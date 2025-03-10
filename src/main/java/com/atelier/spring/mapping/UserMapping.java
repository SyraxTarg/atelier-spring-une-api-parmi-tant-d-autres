package com.atelier.spring.mapping;


import com.atelier.spring.DTO.AddressDTO;
import com.atelier.spring.DTO.UserDTO;
import com.atelier.spring.entity.Address;
import com.atelier.spring.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapping {
    public User dtoToEntity(UserDTO userDTO){
        return User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();
    }

    public UserDTO entityToDTO(User user){
        return UserDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
