package com.work.hotelReservation.adminservice.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class DiscountPayload {
    @NotBlank
    private String name;
    @NotNull
    private LocalDate applicableFrom;
    @NotNull
    private LocalDate applicableTill;
    @NotNull
    private Float percentage;
}
