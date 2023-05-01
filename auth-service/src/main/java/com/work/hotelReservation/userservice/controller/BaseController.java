package com.work.hotelReservation.userservice.controller;

import com.work.hotelReservation.userservice.service.UserService;

public class BaseController {


    final UserService userService;

    public BaseController(UserService userService){
        this.userService = userService;
    }


}
