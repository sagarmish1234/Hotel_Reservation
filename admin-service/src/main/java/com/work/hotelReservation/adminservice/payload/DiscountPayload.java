package com.work.hotelReservation.adminservice.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class DiscountPayload {
    @NotBlank
    private String name;
    @NotNull
    private Date applicableFrom;
    @NotNull
    private Date applicableTill;
    @NotNull
    private Float percentage;
}
