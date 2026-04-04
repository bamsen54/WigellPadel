package com.simon.wigellpadel.service;

import com.simon.wigellpadel.dto.CourtDto;
import com.simon.wigellpadel.dto.PostCourtDto;
import com.simon.wigellpadel.dto.PutCourtDto;
import com.simon.wigellpadel.entity.Court;
import com.simon.wigellpadel.exception.CourtDoesNotExistException;
import com.simon.wigellpadel.exception.CourtNameNotAvailableException;
import com.simon.wigellpadel.mapper.CourtMapper;
import com.simon.wigellpadel.repository.CourtRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourtService {

    private final CourtRepository courtRepository;
    private static final Logger logger = LoggerFactory.getLogger(CourtService.class);


    @Value("${app.exchange.sek-to-eur}")
    private double exchangeSekToEur;

    @Value("${app.exchange.eur-to-sek}")
    private double exchangeEurToSek;

    public CourtService(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    public List<CourtDto> findAllCourts() {
        List<Court> courts = courtRepository.findAll();
        return courts.stream().map(CourtMapper::toDto).toList();
    }

    public CourtDto findCourtById(Long courtId) {
        Court court = courtRepository.findById(courtId).orElseThrow(() -> new RuntimeException("court not found")); // TODO fix custom exception
        return  CourtMapper.toDto(court);
    }

    @Transactional
    public CourtDto save(PostCourtDto dto) {

        if(courtWithCourtNameAlreadyExists(dto.courtName(), null)) {
            logger.warn("court with courtName: {} already exists - creation attempt rejected", dto.courtName());
            throw new CourtNameNotAvailableException("Court with courtName " + dto.courtName() + " already exists" );
        }

        Court court = CourtMapper.fromPostDto(dto);

        court.setPricePerHourEur(exchangeSekToEur * court.getPricePerHourSek());

        courtRepository.save(court);

        logger.info("court with id: {} saved", court.getId().toString());


        return CourtMapper.toDto(court);
    }

    public CourtDto update(Long id, PutCourtDto dto) {
        Court court = courtRepository.findById(id).orElseThrow(() -> {
            logger.warn("court with id: {} does not exist", id);
            throw new CourtDoesNotExistException(id);
        });

        if(courtWithCourtNameAlreadyExists(dto.courtName(), id)) {
            logger.warn("court with courtName: {} already exists - update attempt rejected", dto.courtName());
            throw new CourtNameNotAvailableException("Court with courtName " + dto.courtName() + " already exists" );
        }

        court.setCourtName(dto.courtName());
        court.setPricePerHourSek(dto.pricePerHourSek());
        court.setPricePerHourEur(exchangeEurToSek * court.getPricePerHourSek());

        Court updated = courtRepository.save(court);

        logger.info("court with id: {} updated", court.getId().toString());


        return CourtMapper.toDto(updated);
    }

    public void delete(Long id) {
        Court court = courtRepository.findById(id).orElseThrow(() -> {
            logger.warn("court with id {} does not exist - delete attempt rejected", id);
            throw new CourtDoesNotExistException(id);
        });

        logger.info("court with id: {} deleted", court.getId().toString());

        courtRepository.deleteById(id);
    }

    public boolean courtWithCourtNameAlreadyExists(String courtName, Long id) {

        List<Court> courts = courtRepository.findAll();
        for (Court court : courts) {

            if (court.getCourtName().equals(courtName) && !court.getId().equals(id))
                return true;
        }

        return false;
    }
}
