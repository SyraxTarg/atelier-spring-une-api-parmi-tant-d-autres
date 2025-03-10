package com.atelier.spring.DTO;

import lombok.Builder;

import java.util.Date;

@Builder
public record ClientDTO (
        String uid,
        String first_name,
        String last_name,
        String gender,
        String phone_number,
        String email,
        String username,
        Date date_of_birth,
        AddressDTO address
){
}
