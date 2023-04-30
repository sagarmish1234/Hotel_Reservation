package com.work.hotelReservation.adminservice.model;

import com.work.hotelReservation.adminservice.payload.CancellationPayload;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="CancellationPolicies")
@Entity
public class CancellationPolicy implements BaseModel<CancellationPayload> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer noOfDaysBeforeCheckIn;

    private Float cancellationPercentage;

    @Override
    public void fromPayload(CancellationPayload payload) {
        this.noOfDaysBeforeCheckIn = payload.getNoOfDaysBeforeCheckIn();
        this.cancellationPercentage = payload.getCancellationPercentage();
    }
}
