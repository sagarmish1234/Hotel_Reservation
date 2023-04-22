package com.work.hotelReservation.adminservice.controller;

import com.work.hotelReservation.adminservice.model.Discount;
import com.work.hotelReservation.adminservice.payload.DiscountPayload;
import com.work.hotelReservation.adminservice.service.DiscountService;
import com.work.hotelReservation.adminservice.service.HotelService;
import com.work.hotelReservation.adminservice.service.RoomService;
import com.work.hotelReservation.adminservice.service.VoucherService;
import com.work.hotelReservation.adminservice.utils.ApiUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discount")
public class DiscountController extends BaseController<Discount, DiscountPayload> {
    @Autowired
    public DiscountController(DiscountService discountService, HotelService hotelService, RoomService service, VoucherService voucherService) {
        super(discountService, hotelService, service, voucherService);
    }

    @Override
    public ResponseEntity<?> createModel(@Valid @RequestBody DiscountPayload payload) {
        try{
            discountService.saveModel(payload);
            return ResponseEntity.ok(ApiUtil.generateResponse("message","Saved successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ApiUtil.generateResponse("message",e.getMessage()));
        }

    }

    @Override
    public ResponseEntity<?> updateModel(@PathVariable Long id, @Valid @RequestBody DiscountPayload payload) {
        try{
            discountService.updateModel(id,payload);
            return ResponseEntity.ok(ApiUtil.generateResponse("message","Updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ApiUtil.generateResponse("message",e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> getModel(@PathVariable Long id) {
        try{
            Discount model = discountService.getModel(id);
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ApiUtil.generateResponse("message",e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> deleteModel(@PathVariable Long id) {
            discountService.deleteModel(id);
            return ResponseEntity.ok(ApiUtil.generateResponse("message","Deleted successfully"));
    }
}
