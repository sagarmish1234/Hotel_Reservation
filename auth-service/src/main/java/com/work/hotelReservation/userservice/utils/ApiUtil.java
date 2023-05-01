package com.work.hotelReservation.userservice.utils;

import java.util.HashMap;

public class ApiUtil {

    public static HashMap<Object, Object> generateResponse(String key, String value){
        HashMap<Object, Object> response = new HashMap<>();
        response.put(key,value);
        return response;
    }

}
