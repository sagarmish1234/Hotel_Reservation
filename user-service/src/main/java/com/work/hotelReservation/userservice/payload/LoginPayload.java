package com.work.hotelReservation.userservice.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginPayload {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
