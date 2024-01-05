package com.app.appbackend.service.impl;

import com.app.appbackend.entity.Role;
import com.app.appbackend.entity.User;
import com.app.appbackend.model.request.SignUpRequest;
import com.app.appbackend.model.request.SigninRequest;
import com.app.appbackend.model.response.JwtAuthenticationResponse;
import com.app.appbackend.model.response.UserLoggedResponse;
import com.app.appbackend.model.response.UsersResponse;
import com.app.appbackend.repository.UserRepository;
import com.app.appbackend.service.AuthenticationService;
import com.app.appbackend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public List<UsersResponse> usuariosCadastrados() {
        return userRepository.findAll().stream().map(usuarios ->
                modelMapper.map(usuarios, UsersResponse.class)).toList();
    }

    @Override
    public UserLoggedResponse obterUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return obterNomeUsuarioLogado(authentication.getName());
    }

    public UserLoggedResponse obterNomeUsuarioLogado(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário logado não encontrado"));
        return UserLoggedResponse.builder()
                .userLogged(user.getFirstName())
                .build();
    }
}
