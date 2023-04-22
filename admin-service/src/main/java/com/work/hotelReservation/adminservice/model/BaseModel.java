package com.work.hotelReservation.adminservice.model;

public interface BaseModel<T> {

    public void fromPayload(T payload);

}
