package com.work.hotelReservation.adminservice.model;

import com.work.hotelReservation.adminservice.payload.HotelPayload;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Hotels")
@Data
public class Hotel implements BaseModel<HotelPayload>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String city;

    @Override
    public  void fromPayload(HotelPayload payload) {
        this.name = payload.getName();
        this.city = payload.getCity();
        this.address = payload.getAddress();
        this.phone = payload.getPhone();
    }
}
