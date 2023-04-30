package com.work.hotelReservation.adminservice.controller;

import com.work.hotelReservation.adminservice.model.Room;
import com.work.hotelReservation.adminservice.payload.RoomPayload;
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
@RequestMapping("/api/room")
public class RoomController extends BaseController<Room , RoomPayload>{
    @Autowired
    public RoomController(DiscountService discountService, HotelService hotelService, RoomService roomService, VoucherService voucherService, CancellationPolicyService cancellationPolicyService) {
        super(discountService, hotelService, roomService, voucherService, cancellationPolicyService);
    }

    @Override
    public ResponseEntity<?> createModel(@Valid @RequestBody RoomPayload payload) {
        try{
            roomService.saveModel(payload);
            return ResponseEntity.ok(ApiUtil.generateResponse("message","Saved Successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ApiUtil.generateResponse("message",e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> updateModel(@PathVariable Long id, @Valid @RequestBody RoomPayload payload) {
        try {
            roomService.updateModel(id, payload);
            return ResponseEntity.ok(ApiUtil.generateResponse("message","Updated Successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ApiUtil.generateResponse("message",e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> getModel(@PathVariable Long id) {
        try {
            Room model = roomService.getModel(id);
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ApiUtil.generateResponse("message",e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> deleteModel(@PathVariable Long id) {
        roomService.deleteModel(id);
        return ResponseEntity.ok(ApiUtil.generateResponse("message","Deleted Successfully"));

    }
}
