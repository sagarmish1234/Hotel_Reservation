package com.work.hotelReservation.adminservice.service;

import com.work.hotelReservation.adminservice.model.Hotel;
import com.work.hotelReservation.adminservice.payload.HotelPayload;
import com.work.hotelReservation.adminservice.repository.DiscountRepository;
import com.work.hotelReservation.adminservice.repository.HotelRepository;
import com.work.hotelReservation.adminservice.repository.RoomRepositiory;
import com.work.hotelReservation.adminservice.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService extends BaseService<HotelPayload,Hotel>{

    @Autowired
    public HotelService(HotelRepository hotelRepository, DiscountRepository discountRepository, VoucherRepository voucherRepository, RoomRepositiory roomRepositiory) {
        super(hotelRepository, discountRepository, voucherRepository, roomRepositiory);
    }

    @Override
    public  void saveModel(HotelPayload payload) {
        Hotel hotel = new Hotel();
        hotel.fromPayload(payload);
        hotelRepository.save(hotel);
    }

    @Override
    public  void updateModel(Long id, HotelPayload payload) throws Exception {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        if(hotel==null)
            throw new Exception("Not hotel found for the given id");
        hotel.fromPayload(payload);
        hotelRepository.save(hotel);
    }

    @Override
    public Hotel getModel(Long id) throws Exception {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        if(hotel==null)
            throw new Exception("Not hotel found for the given id");
        return hotel;
    }

    @Override
    public  void  deleteModel(Long id) {
        hotelRepository.deleteById(id);
    }

}
