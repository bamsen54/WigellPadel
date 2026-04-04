package com.simon.wigellpadel.controller;

import com.simon.wigellpadel.dto.CourtDto;
import com.simon.wigellpadel.dto.PostCourtDto;
import com.simon.wigellpadel.dto.PutCourtDto;
import com.simon.wigellpadel.service.CourtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AdminCourtController {

    private final CourtService courtService;

    public AdminCourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping("/courts")
    public ResponseEntity<List<CourtDto>> getAllCourts() {
        List<CourtDto> allCourts = courtService.findAllCourts();
        return ResponseEntity.status(HttpStatus.OK).body(allCourts);
    }

    @GetMapping("/courts/{courtId}")
    public ResponseEntity<CourtDto> getCourtById(@PathVariable Long courtId) {
        return  ResponseEntity.status(HttpStatus.OK).body(courtService.findCourtById(courtId));
    }

    @PostMapping("/courts")
    public ResponseEntity<CourtDto> postCourt(@Valid @RequestBody PostCourtDto dto) {
        CourtDto courtDto = courtService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(courtDto);
    }

    @PutMapping("/courts/{courtId}")
    public ResponseEntity<CourtDto> putCourt(@PathVariable Long courtId, @Valid @RequestBody PutCourtDto dto) {
        CourtDto updatedCourt = courtService.update(courtId, dto);
        return ResponseEntity.ok(updatedCourt);
    }

    @DeleteMapping("/courts/{courtId}")
    public ResponseEntity<CourtDto> deleteCourt(@PathVariable Long courtId) {
        courtService.delete(courtId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
