package com.work.hotelReservation.adminservice.service;

import com.work.hotelReservation.adminservice.model.Voucher;
import com.work.hotelReservation.adminservice.payload.VoucherPayload;
import com.work.hotelReservation.adminservice.repository.DiscountRepository;
import com.work.hotelReservation.adminservice.repository.HotelRepository;
import com.work.hotelReservation.adminservice.repository.RoomRepositiory;
import com.work.hotelReservation.adminservice.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoucherService extends BaseService<VoucherPayload, Voucher> {
    @Autowired
    public VoucherService(HotelRepository hotelRepository, DiscountRepository discountRepository, VoucherRepository voucherRepository, RoomRepositiory roomRepositiory) {
        super(hotelRepository, discountRepository, voucherRepository, roomRepositiory);
    }

    @Override
    public void saveModel(VoucherPayload payload) {
        Voucher voucher = new Voucher();
        voucher.fromPayload(payload);
        voucher.setIsRedeemed(false);
        voucherRepository.save(voucher);
    }

    @Override
    public void updateModel(Long id, VoucherPayload payload) throws Exception {
        Voucher voucher = voucherRepository.findById(id).orElse(null);
        if(voucher==null)
            throw new Exception("Voucher not found");
        voucher.fromPayload(payload);
        voucherRepository.save(voucher);
    }

    @Override
    public Voucher getModel(Long id) throws Exception {
        Voucher voucher = voucherRepository.findById(id).orElse(null);
        if(voucher==null)
            throw new Exception("Voucher not found");
        return voucher;
    }

    @Override
    public void deleteModel(Long id) {
        voucherRepository.deleteById(id);
    }
}
