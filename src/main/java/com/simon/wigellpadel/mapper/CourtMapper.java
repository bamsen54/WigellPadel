package com.simon.wigellpadel.mapper;

import com.simon.wigellpadel.dto.CourtDto;
import com.simon.wigellpadel.dto.PostCourtDto;
import com.simon.wigellpadel.entity.Court;

public class CourtMapper {

    public static CourtDto toDto(Court court) {

        return new CourtDto(
            court.getId(),
            court.getCourtName(),
            court.getPricePerHourSek(),
            court.getPricePerHourEur()
        );
    }

    public static Court fromPostDto(PostCourtDto dto) {
        Court court = new Court();

        court.setCourtName(dto.courtName());
        court.setPricePerHourSek(dto.pricePerHourSek());

        return court;
    }
}
