package com.work.hotelReservation.adminservice.payload;

import lombok.Data;

@Data
public class CancellationPayload {

    private Integer noOfDaysBeforeCheckIn;

    private Float cancellationPercentage;
}
