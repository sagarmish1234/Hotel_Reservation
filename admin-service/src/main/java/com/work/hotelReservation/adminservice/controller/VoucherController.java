package com.work.hotelReservation.adminservice.controller;

import com.work.hotelReservation.adminservice.model.Voucher;
import com.work.hotelReservation.adminservice.payload.VoucherPayload;
import com.work.hotelReservation.adminservice.service.*;
import com.work.hotelReservation.adminservice.utils.ApiUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/voucher")
public class VoucherController extends BaseController<Voucher, VoucherPayload>{
    @Autowired
    public VoucherController(DiscountService discountService, HotelService hotelService, RoomService roomService, VoucherService voucherService, CancellationPolicyService cancellationPolicyService) {
        super(discountService, hotelService, roomService, voucherService, cancellationPolicyService);
    }

    @Override
    public ResponseEntity<?> createModel(@Valid @RequestBody VoucherPayload payload) {
        try{
            voucherService.saveModel(payload);
            return ResponseEntity.ok(ApiUtil.generateResponse("message","Saved Successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ApiUtil.generateResponse("message",e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> updateModel(@PathVariable Long id, @Valid @RequestBody VoucherPayload payload) {
        try {
            voucherService.updateModel(id, payload);
            return ResponseEntity.ok(ApiUtil.generateResponse("message","Updated Successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ApiUtil.generateResponse("message",e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> getModel(@PathVariable Long id) {
        try {
            Voucher model = voucherService.getModel(id);
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ApiUtil.generateResponse("message",e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> deleteModel(@PathVariable Long id) {
        voucherService.deleteModel(id);
        return ResponseEntity.ok(ApiUtil.generateResponse("message","Deleted Successfully"));

    }
}
