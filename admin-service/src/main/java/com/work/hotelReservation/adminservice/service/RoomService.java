package com.work.hotelReservation.adminservice.service;

import com.work.hotelReservation.adminservice.model.Hotel;
import com.work.hotelReservation.adminservice.model.Room;
import com.work.hotelReservation.adminservice.payload.RoomPayload;
import com.work.hotelReservation.adminservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService extends BaseService<RoomPayload, Room>{


    public RoomService(HotelRepository hotelRepository, DiscountRepository discountRepository, VoucherRepository voucherRepository, RoomRepositiory roomRepositiory, CancellationPolicyRepository cancellationPolicyRepository) {
        super(hotelRepository, discountRepository, voucherRepository, roomRepositiory, cancellationPolicyRepository);
    }

    @Override
    public void saveModel(RoomPayload payload) throws Exception {
        Room room = new Room();
        room.fromPayload(payload);
        Hotel hotel = hotelRepository.findById(payload.getHotelId()).orElse(null);
        if(hotel==null)
            throw new Exception("Hotel not found");
        room.setHotel(hotel);
        roomRepositiory.save(room);
    }

    @Override
    public void updateModel(Long id, RoomPayload payload) throws Exception {
        Room room = roomRepositiory.findById(id).orElse(null);
        if(room==null)
            throw new Exception("Room not found");
        room.fromPayload(payload);
        Hotel hotel = hotelRepository.findById(payload.getHotelId()).orElse(null);
        if(hotel==null)
            throw new Exception("Hotel not found");
        room.setHotel(hotel);
        roomRepositiory.save(room);
    }

    @Override
    public Room getModel(Long id) throws Exception {
        Room room = roomRepositiory.findById(id).orElse(null);
        if(room==null)
            throw new Exception("Room not found");
        return room;
    }

    @Override
    public void deleteModel(Long id) {
        roomRepositiory.deleteById(id);
    }
}
