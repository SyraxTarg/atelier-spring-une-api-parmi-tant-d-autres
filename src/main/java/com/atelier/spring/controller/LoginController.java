package com.atelier.spring.controller;

import com.atelier.spring.config.JwtTokenProvider;
import com.atelier.spring.DTO.AuthResponseDTO;
import com.atelier.spring.DTO.LoginDTO;
import com.atelier.spring.entity.User;
import com.atelier.spring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginController(AuthenticationManager authenticationManager, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDto) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Gather the infos on user
            String userName = loginDto.getUsername();
            User user = userService.getUser(loginDto.getUsername());

            List<String> roles = userService.getUserRoles(user);

            // Create security token for user
            String token = jwtTokenProvider.generateToken(userName, roles);

            return ResponseEntity.ok(new AuthResponseDTO(token, userName));
        } catch (AuthenticationException e) {


            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }

}
