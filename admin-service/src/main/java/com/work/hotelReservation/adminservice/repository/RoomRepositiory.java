package com.work.hotelReservation.adminservice.repository;

import com.work.hotelReservation.adminservice.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepositiory extends JpaRepository<Room,Long> {
}
