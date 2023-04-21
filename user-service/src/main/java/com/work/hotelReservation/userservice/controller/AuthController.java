package com.work.hotelReservation.userservice.controller;

import com.work.hotelReservation.userservice.payload.LoginPayload;
import com.work.hotelReservation.userservice.payload.UserPayload;
import com.work.hotelReservation.userservice.service.UserService;
import com.work.hotelReservation.userservice.utils.ApiUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @Autowired
    public AuthController(UserService userService) {
        super(userService);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginPayload payload) {
        try {
            String token = userService.loginUser(payload);
            HashMap<Object, Object> response = ApiUtil.generateResponse("token", token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(ApiUtil.generateResponse("message", e.getMessage()));
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserPayload payload){
        try {
            userService.registerUser(payload);
            HashMap<Object, Object> response = ApiUtil.generateResponse("message", "User registered successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(ApiUtil.generateResponse("message",e.getMessage()));
        }
    }



}
