package com.atelier.spring.mapping;

import com.atelier.spring.DTO.AddressDTO;
import com.atelier.spring.DTO.ClientDTO;
import com.atelier.spring.entity.Address;
import com.atelier.spring.entity.Client;
import com.atelier.spring.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientMapping {

    private final AddressRepository addressRepository;
    private final AddressMapping addressMapping;

    public Client dtoToEntity(ClientDTO clientDTO){

        Address address = Address.builder()
                .city(clientDTO.address().city())
                .zip_code(clientDTO.address().zip_code())
                .country(clientDTO.address().country())
                .build();
        addressRepository.save(address);

        return Client.builder()
                .first_name(clientDTO.first_name())
                .last_name(clientDTO.last_name())
                .uid(clientDTO.uid())
                .gender(clientDTO.gender())
                .phone_number(clientDTO.phone_number())
                .email(clientDTO.email())
                .username(clientDTO.username())
                .date_of_birth(clientDTO.date_of_birth())
                .address(address)
                .build();
    }

    public ClientDTO entityToDTO(Client client){
        return ClientDTO.builder()
                .first_name(client.getFirst_name())
                .last_name(client.getLast_name())
                .uid(client.getUid())
                .gender(client.getGender())
                .phone_number(client.getPhone_number())
                .email(client.getEmail())
                .username(client.getUsername())
                .date_of_birth(client.getDate_of_birth())
                .address(addressMapping.entityToDTO(client.getAddress()))
                .build();
    }
}

