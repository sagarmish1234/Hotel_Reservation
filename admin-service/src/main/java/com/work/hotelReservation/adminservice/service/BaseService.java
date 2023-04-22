package com.work.hotelReservation.adminservice.service;


import com.work.hotelReservation.adminservice.repository.DiscountRepository;
import com.work.hotelReservation.adminservice.repository.HotelRepository;
import com.work.hotelReservation.adminservice.repository.RoomRepositiory;
import com.work.hotelReservation.adminservice.repository.VoucherRepository;

public abstract class BaseService<P,T> {
    final HotelRepository hotelRepository;
    final DiscountRepository discountRepository;
    final VoucherRepository voucherRepository;
    final RoomRepositiory roomRepositiory;

    public BaseService(HotelRepository hotelRepository, DiscountRepository discountRepository, VoucherRepository voucherRepository, RoomRepositiory roomRepositiory) {
        this.hotelRepository = hotelRepository;
        this.discountRepository = discountRepository;
        this.voucherRepository = voucherRepository;
        this.roomRepositiory = roomRepositiory;
    }

    public abstract  void saveModel(P payload) throws Exception;
    public abstract void updateModel(Long id,P payload) throws Exception;
    public abstract T getModel(Long id) throws Exception;
    public abstract  void deleteModel(Long id);
}
