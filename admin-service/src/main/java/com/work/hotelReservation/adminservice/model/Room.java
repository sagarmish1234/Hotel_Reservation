package com.work.hotelReservation.adminservice.model;

import com.work.hotelReservation.adminservice.payload.RoomPayload;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Rooms")
@Data
public class Room implements BaseModel<RoomPayload>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;

    private String type;

    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    private Hotel hotel;

    @Override
    public void fromPayload(RoomPayload payload) {
        this.number = payload.getNumber();
        this.type = payload.getType();
        this.price = payload.getPrice();
    }
}
