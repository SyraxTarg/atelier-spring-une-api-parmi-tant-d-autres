package com.atelier.spring.DTO;

import lombok.Builder;

@Builder
public record AddressDTO (
        String city,
        String zip_code,
        String country
){
}
