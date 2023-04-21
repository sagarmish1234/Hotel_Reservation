package com.work.hotelReservation.userservice.service;

import com.work.hotelReservation.userservice.model.User;
import com.work.hotelReservation.userservice.repository.RoleRepository;
import com.work.hotelReservation.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailsService extends BaseService implements UserDetailsService {

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        super(userRepository, roleRepository);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElse(null);
        if(user==null)
            throw  new UsernameNotFoundException("Username not found");
        return user;
    }
}
