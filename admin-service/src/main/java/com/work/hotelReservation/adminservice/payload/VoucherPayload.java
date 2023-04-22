package com.work.hotelReservation.adminservice.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class VoucherPayload {

    @NotNull
    private Integer code;
    @NotNull
    private Double price;

    private Boolean isRedeemed;
}
