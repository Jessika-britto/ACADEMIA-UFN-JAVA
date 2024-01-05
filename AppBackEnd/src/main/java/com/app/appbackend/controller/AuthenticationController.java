package com.app.appbackend.controller;

import com.app.appbackend.model.request.SignUpRequest;
import com.app.appbackend.model.request.SigninRequest;
import com.app.appbackend.model.response.JwtAuthenticationResponse;
import com.app.appbackend.model.response.UsersResponse;
import com.app.appbackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("register")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("login")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @GetMapping("users")
    public ResponseEntity<List<UsersResponse>> usuariosCadastrados(){
        return  ResponseEntity.ok().body(authenticationService.usuariosCadastrados());
    }
}
