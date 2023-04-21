package com.work.hotelReservation.userservice.repository;

import com.work.hotelReservation.userservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {


    Role findByName(String name);
}
