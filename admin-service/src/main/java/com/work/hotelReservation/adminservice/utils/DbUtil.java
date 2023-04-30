package com.work.hotelReservation.adminservice.utils;

public class DbUtil {

    public static String HOTEL_QUERY = "INSERT INTO hotels (address,city,name,phone) VALUES (:address,:city,:name,:phone)";

    public static String DISCOUNT_QUERY = "INSERT INTO discounts (applicable_from,applicable_till,name,percentage) VALUES (:applicableFrom,:applicableTill,:name,:percentage)";

}
