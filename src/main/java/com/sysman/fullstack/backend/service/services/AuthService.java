package com.sysman.fullstack.backend.service.services;

import com.sysman.fullstack.backend.config.security.JwtService;
import com.sysman.fullstack.backend.model.request.LoginRequest;
import com.sysman.fullstack.backend.model.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getDocumentNumber(), request.getPassword())
        );
        String token = jwtService.generateToken((UserDetails) auth.getPrincipal());
        return new LoginResponse(token);
    }
}
