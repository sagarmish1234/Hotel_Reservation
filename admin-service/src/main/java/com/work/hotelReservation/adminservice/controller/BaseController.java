package com.work.hotelReservation.adminservice.controller;

import com.work.hotelReservation.adminservice.service.DiscountService;
import com.work.hotelReservation.adminservice.service.HotelService;
import com.work.hotelReservation.adminservice.service.RoomService;
import com.work.hotelReservation.adminservice.service.VoucherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseController<T,P> {

    final DiscountService discountService;
    final HotelService hotelService;
    final RoomService roomService;
    final VoucherService voucherService;

    public BaseController(DiscountService discountService, HotelService hotelService, RoomService roomService, VoucherService voucherService) {
        this.discountService = discountService;
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.voucherService = voucherService;
    }

    @PostMapping("/create")
    public abstract ResponseEntity<?> createModel( P payload);

    @PutMapping("/update/{id}")
    public abstract  ResponseEntity<?> updateModel( Long id, P payload);

    @GetMapping("/get/{id}")
    public abstract ResponseEntity<?> getModel( Long id);

    @DeleteMapping("/delete/{id}")
    public abstract ResponseEntity<?> deleteModel( Long id);
}
