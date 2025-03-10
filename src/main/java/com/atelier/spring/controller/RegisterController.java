package com.atelier.spring.controller;

import com.atelier.spring.DTO.UserDTO;
import com.atelier.spring.entity.User;
import com.atelier.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    // register with role_user

    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO user) {
        userService.registerNewUserAccountUser(user);
        return ResponseEntity.ok("User user registered successfully");
    }

    // register with role_rebond

    @PostMapping("/register/rebond")
    public ResponseEntity<?> registerRebond(@RequestBody UserDTO user) {
        userService.registerNewUserAccountRebond(user);
        return ResponseEntity.ok("User rebond registered successfully");
    }

    // register with role_scrap

    @PostMapping("/register/scrapper")
    public ResponseEntity<?> registerScrapper(@RequestBody UserDTO user) {
        userService.registerNewUserAccountScrap(user);
        return ResponseEntity.ok("User scrapper registered successfully");
    }

}