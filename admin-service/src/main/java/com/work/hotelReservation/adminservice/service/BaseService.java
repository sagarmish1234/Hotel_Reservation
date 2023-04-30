package com.work.hotelReservation.adminservice.service;


import com.work.hotelReservation.adminservice.repository.*;

public abstract class BaseService<P,T> {
    final HotelRepository hotelRepository;
    final DiscountRepository discountRepository;
    final VoucherRepository voucherRepository;
    final RoomRepositiory roomRepositiory;

    final CancellationPolicyRepository cancellationPolicyRepository;

    public BaseService(HotelRepository hotelRepository, DiscountRepository discountRepository, VoucherRepository voucherRepository, RoomRepositiory roomRepositiory, CancellationPolicyRepository cancellationPolicyRepository) {
        this.hotelRepository = hotelRepository;
        this.discountRepository = discountRepository;
        this.voucherRepository = voucherRepository;
        this.roomRepositiory = roomRepositiory;
        this.cancellationPolicyRepository = cancellationPolicyRepository;
    }

    public abstract  void saveModel(P payload) throws Exception;
    public abstract void updateModel(Long id,P payload) throws Exception;
    public abstract T getModel(Long id) throws Exception;
    public abstract  void deleteModel(Long id);
}
