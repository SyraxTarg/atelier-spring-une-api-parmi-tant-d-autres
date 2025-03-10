package com.atelier.spring.mapping;


import com.atelier.spring.DTO.AddressDTO;
import com.atelier.spring.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapping {
    public Address dtoToEntity(AddressDTO addressDTO){
        return Address.builder()
                .city(addressDTO.city())
                .zip_code(addressDTO.zip_code())
                .country(addressDTO.country())
                .build();
    }

    public AddressDTO entityToDTO(Address address){
        return AddressDTO.builder()
                .city(address.getCity())
                .zip_code(address.getZip_code())
                .country(address.getCountry())
                .build();
    }
}
