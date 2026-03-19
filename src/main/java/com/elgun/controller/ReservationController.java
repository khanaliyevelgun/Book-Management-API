package com.elgun.controller;

import com.elgun.Dto.ReservationRequestDto;
import com.elgun.Dto.ReservationResponseDto;
import com.elgun.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
@Validated
@Tag(name = "Reservations", description = "Reservation management endpoints")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create reservation ")
    public void createReservation(@RequestBody @Valid ReservationRequestDto requestDto){
        reservationService.createReservation(requestDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get reservation by ID")
    public ReservationResponseDto getReservationById(@PathVariable @Min(1) Long id){
        return reservationService.getReservationById(id);
    }
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all reservations by page and size")
    public Page<ReservationResponseDto> getAllReservationsByUser(@PathVariable @Min(1) Long userId,@RequestParam(defaultValue = "0") @Min(0) int page,@RequestParam(defaultValue = "10") @Min(1) int size){
        return reservationService.getAllReservationsByUser(userId,page,size);
    }

    @PatchMapping("/{id}/approve")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Approve reservation")
    public void approveReservation(@PathVariable @Min(1) Long id){
        reservationService.approveReservation(id);
    }
    @PatchMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Cancel reservation")
    public void cancelReservation(@PathVariable @Min(1) Long id){
        reservationService.cancelReservation(id);
    }


}

