package com.work.hotelReservation.adminservice.utils;

import java.util.HashMap;

public class ApiUtil {

    public static HashMap<Object, Object> generateResponse(String key, String value){
        HashMap<Object, Object> response = new HashMap<>();
        response.put(key,value);
        return response;
    }

    public static String ABSOLUTE_PATH = "D:\\Projects\\hotelReservation\\admin-service\\src\\main\\resources\\";

    public static String HOTEL_JOB_STRING = "hotel";

    public static String DISCOUNT_JOB_STRING = "discount";

    public static String ROOM_JOB_STRING = "room";

    public static String VOUCHER_JOB_STRING = "voucher";
}
