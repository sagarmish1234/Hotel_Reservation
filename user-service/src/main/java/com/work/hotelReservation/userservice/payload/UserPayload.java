package com.work.hotelReservation.userservice.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserPayload {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
}
