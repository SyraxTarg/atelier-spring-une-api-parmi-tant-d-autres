package com.atelier.spring.service;

import com.atelier.spring.DTO.UserDTO;
import com.atelier.spring.entity.Role;
import com.atelier.spring.entity.User;
import com.atelier.spring.mapping.UserMapping;
import com.atelier.spring.repository.RoleRepository;
import com.atelier.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapping userMapping;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserMapping userMapping, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapping = userMapping;
        this.roleRepository = roleRepository;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User registerNewUserAccountUser(UserDTO userDTO) {
        User user = userMapping.dtoToEntity(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        user.setEnabled(true);
        return userRepository.save(user);
    }

    public User registerNewUserAccountRebond(UserDTO userDTO) {
        User user = userMapping.dtoToEntity(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_REBOND"));
        user.setRoles(roles);
        user.setEnabled(true);
        return userRepository.save(user);
    }

    public User registerNewUserAccountScrap(UserDTO userDTO) {
        User user = userMapping.dtoToEntity(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_SCRAP"));
        user.setRoles(roles);
        user.setEnabled(true);
        return userRepository.save(user);
    }

    public User getUserByToken(String token) {
        User user = userRepository.findByClaimToken(token);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else if (user.getTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new UsernameNotFoundException("Token expired");
        } else if (user.isEnabled()) {
            throw new UsernameNotFoundException("User already enabled");
        }

        return user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<String> getUserRoles(String username) {
        User user = getUser(username);
        return getUserRoles(user);
    }

    public List<String> getUserRoles(User user) {
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
