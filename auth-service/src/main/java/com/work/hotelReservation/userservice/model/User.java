package com.work.hotelReservation.userservice.model;

import com.work.hotelReservation.userservice.payload.UserPayload;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Users")
public class User implements UserDetails,BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public void fromPayload(Object payload) {
        UserPayload userPayload = (UserPayload) payload;
        this.setEmail(userPayload.getEmail());
        this.setPassword(userPayload.getPassword());
        this.setAddress(userPayload.getAddress());
        this.setFirstName(userPayload.getFirstName());
        this.setLastName(userPayload.getLastName());
        this.setPhone(userPayload.getPhone());
    }
}
