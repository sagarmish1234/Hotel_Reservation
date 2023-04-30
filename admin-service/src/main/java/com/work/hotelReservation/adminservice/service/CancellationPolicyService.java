package com.work.hotelReservation.adminservice.service;

import com.work.hotelReservation.adminservice.model.CancellationPolicy;
import com.work.hotelReservation.adminservice.payload.CancellationPayload;
import com.work.hotelReservation.adminservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancellationPolicyService extends BaseService<CancellationPayload, CancellationPolicy> {

    @Autowired
    public CancellationPolicyService(HotelRepository hotelRepository, DiscountRepository discountRepository, VoucherRepository voucherRepository, RoomRepositiory roomRepositiory, CancellationPolicyRepository cancellationPolicyRepository) {
        super(hotelRepository, discountRepository, voucherRepository, roomRepositiory, cancellationPolicyRepository);
    }

    @Override
    public void saveModel(CancellationPayload payload) throws Exception {
        CancellationPolicy cancellationPolicy = new CancellationPolicy();
        cancellationPolicy.fromPayload(payload);
        cancellationPolicyRepository.save(cancellationPolicy);
    }

    @Override
    public void updateModel(Long id, CancellationPayload payload) throws Exception {
        CancellationPolicy cancellationPolicy = cancellationPolicyRepository.findById(id).orElse(null);
        if (cancellationPolicy == null)
            throw new Exception("No cancellation policy found");
        cancellationPolicy.fromPayload(payload);
        cancellationPolicyRepository.save(cancellationPolicy);
    }

    @Override
    public CancellationPolicy getModel(Long id) throws Exception {
        CancellationPolicy cancellationPolicy = cancellationPolicyRepository.findById(id).orElse(null);
        if (cancellationPolicy == null)
            throw new Exception("No cancellation policy found");
        return cancellationPolicy;
    }

    @Override
    public void deleteModel(Long id) {
        cancellationPolicyRepository.deleteById(id);
    }
}
