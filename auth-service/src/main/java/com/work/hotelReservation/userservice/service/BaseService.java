package com.work.hotelReservation.userservice.service;

import com.work.hotelReservation.userservice.repository.RoleRepository;
import com.work.hotelReservation.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {

    final UserRepository userRepository;

    final RoleRepository roleRepository;

    public BaseService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
}
