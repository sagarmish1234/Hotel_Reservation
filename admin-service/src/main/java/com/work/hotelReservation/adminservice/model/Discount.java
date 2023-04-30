package com.work.hotelReservation.adminservice.model;

import com.work.hotelReservation.adminservice.payload.DiscountPayload;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="Discounts")
@Data
public class Discount implements BaseModel<DiscountPayload> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate applicableFrom;

    private LocalDate applicableTill;

    private Float percentage;

    @Override
    public void fromPayload(DiscountPayload payload) {
        this.name = payload.getName();
        this.applicableFrom = payload.getApplicableFrom();
        this.applicableTill = payload.getApplicableTill();
        this.percentage = payload.getPercentage();
    }
}
