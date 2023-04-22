package com.work.hotelReservation.adminservice.controller;

import com.work.hotelReservation.adminservice.model.Hotel;
import com.work.hotelReservation.adminservice.payload.HotelPayload;
import com.work.hotelReservation.adminservice.service.DiscountService;
import com.work.hotelReservation.adminservice.service.HotelService;
import com.work.hotelReservation.adminservice.service.RoomService;
import com.work.hotelReservation.adminservice.service.VoucherService;
import com.work.hotelReservation.adminservice.utils.ApiUtil;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotel")
public class HotelController extends BaseController<Hotel, HotelPayload>{
    @Autowired
    public HotelController(DiscountService discountService, HotelService hotelService, RoomService roomService, VoucherService voucherService) {
        super(discountService, hotelService, roomService, voucherService);
    }

    @Override
    public ResponseEntity<?> createModel(@Valid @RequestBody HotelPayload payload) {
        try{
            hotelService.saveModel(payload);
            return ResponseEntity.ok(ApiUtil.generateResponse("message","Saved Successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ApiUtil.generateResponse("message",e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> updateModel(@PathVariable Long id, @Valid @RequestBody HotelPayload payload) {
        try {
            hotelService.updateModel(id, payload);
            return ResponseEntity.ok(ApiUtil.generateResponse("message","Updated Successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ApiUtil.generateResponse("message",e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> getModel(@PathVariable Long id) {
        try {
            Hotel model = hotelService.getModel(id);
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ApiUtil.generateResponse("message",e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> deleteModel(@PathVariable Long id) {
        hotelService.deleteModel(id);
        return ResponseEntity.ok(ApiUtil.generateResponse("message","Deleted Successfully"));
    }
}
