package com.work.hotelReservation.userservice.service;

import com.work.hotelReservation.userservice.model.Role;
import com.work.hotelReservation.userservice.model.User;
import com.work.hotelReservation.userservice.payload.LoginPayload;
import com.work.hotelReservation.userservice.payload.UserPayload;
import com.work.hotelReservation.userservice.repository.RoleRepository;
import com.work.hotelReservation.userservice.repository.UserRepository;
import com.work.hotelReservation.userservice.utils.DBUtils;
import com.work.hotelReservation.userservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService {


    private final Role customer;

    private final Role admin;

    private final  PasswordEncoder passwordEncoder;


    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager) {
        super(userRepository, roleRepository);
        this.customer = roleRepository.findByName(DBUtils.roleCustomer);
        this.admin = roleRepository.findByName(DBUtils.roleAdmin);
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    public void registerUser(UserPayload userPayload) throws Exception {
        if(userRepository.findByEmail(userPayload.getEmail()).isPresent())
            throw new Exception("Email taken");
        User user = new User();
        user.fromPayload(userPayload);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(customer);
        System.out.println(customer.getName());
        userRepository.save(user);
    }


    public String loginUser(LoginPayload payload) throws UsernameNotFoundException{

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(payload.getUsername(), payload.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if(userRepository.findByEmail(payload.getUsername()).isEmpty())
            throw new UsernameNotFoundException("User not found");

        String token = JwtUtil.generateToken(authentication);

        return token;
    }
}
