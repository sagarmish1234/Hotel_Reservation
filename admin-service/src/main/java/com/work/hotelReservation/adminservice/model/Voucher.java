package com.work.hotelReservation.adminservice.model;

import com.work.hotelReservation.adminservice.payload.VoucherPayload;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Vouchers")
@Data
public class Voucher implements BaseModel<VoucherPayload> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer code;

    private Double price;


    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isRedeemed;

    @Override
    public void fromPayload(VoucherPayload payload) {
        this.code = payload.getCode();
        this.price = payload.getPrice();
        this.isRedeemed = payload.getIsRedeemed();
    }
}
