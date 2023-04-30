package com.work.hotelReservation.adminservice.controller;

import com.work.hotelReservation.adminservice.model.CancellationPolicy;
import com.work.hotelReservation.adminservice.payload.CancellationPayload;
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
@RequestMapping("/api/cancellationPolicy")
public class CancellationPolicyController extends BaseController<CancellationPolicy, CancellationPayload>{

    @Autowired
    public CancellationPolicyController(DiscountService discountService, HotelService hotelService, RoomService roomService, VoucherService voucherService, CancellationPolicyService cancellationPolicyService) {
        super(discountService, hotelService, roomService, voucherService, cancellationPolicyService);
    }

    @Override
    public ResponseEntity<?> createModel(@Valid @RequestBody CancellationPayload payload) {
        try{
            cancellationPolicyService.saveModel(payload);
            return ResponseEntity.ok(ApiUtil.generateResponse("message","Saved successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ApiUtil.generateResponse("message",e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> updateModel(@PathVariable Long id, @Valid @RequestBody CancellationPayload payload) {
        try{
            cancellationPolicyService.updateModel(id,payload);
            return ResponseEntity.ok(ApiUtil.generateResponse("message","Updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ApiUtil.generateResponse("message",e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> getModel(@PathVariable Long id) {
        try{
            CancellationPolicy model = cancellationPolicyService.getModel(id);
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ApiUtil.generateResponse("message",e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> deleteModel(@PathVariable Long id) {
        cancellationPolicyService.deleteModel(id);
        return ResponseEntity.ok(ApiUtil.generateResponse("message","Deleted successfully"));
    }
}
