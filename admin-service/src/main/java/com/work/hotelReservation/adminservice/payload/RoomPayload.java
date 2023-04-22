package com.work.hotelReservation.adminservice.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoomPayload {
    @NotNull
    private Long number;
    @NotBlank
    private String type;
    @NotNull
    private Double price;
    @NotNull
    private Long hotelId;
}
