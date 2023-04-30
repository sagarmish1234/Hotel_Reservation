package com.work.hotelReservation.adminservice.service;

import com.work.hotelReservation.adminservice.model.Discount;
import com.work.hotelReservation.adminservice.payload.DiscountPayload;
import com.work.hotelReservation.adminservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService extends BaseService<DiscountPayload,Discount> {
    @Autowired
    public DiscountService(HotelRepository hotelRepository, DiscountRepository discountRepository, VoucherRepository voucherRepository, RoomRepositiory roomRepositiory, CancellationPolicyRepository cancellationPolicyRepository) {
        super(hotelRepository, discountRepository, voucherRepository, roomRepositiory, cancellationPolicyRepository);
    }



    @Override
    public void saveModel(DiscountPayload payload) {
        Discount discount = new Discount();
        discount.fromPayload(payload);
        discountRepository.save(discount);
    }

    @Override
    public void updateModel(Long id, DiscountPayload payload) throws Exception {
        Discount discount= discountRepository.findById(id).orElse(null);
        if(discount==null) {
            throw new Exception("Discount not found for this id");
        }
        discount.fromPayload(payload);
        discountRepository.save(discount);
    }

    @Override
    public Discount getModel(Long id) throws Exception {
        Discount discount= discountRepository.findById(id).orElse(null);
        if(discount==null)
            throw new Exception("Discount not found for this id");
        return discount;
    }

    @Override
    public void deleteModel(Long id) {
        discountRepository.deleteById(id);
    }
}
