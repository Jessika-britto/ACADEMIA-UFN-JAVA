package com.app.appbackend.controller;

import com.app.appbackend.model.request.SignUpRequest;
import com.app.appbackend.model.request.SigninRequest;
import com.app.appbackend.model.request.UsersRequest;
import com.app.appbackend.model.response.JwtAuthenticationResponse;
import com.app.appbackend.model.response.UserLoggedResponse;
import com.app.appbackend.model.response.UsersResponse;
import com.app.appbackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("usuario-logado")
    public ResponseEntity<UserLoggedResponse> obterUsuarioLogado() {
        return ResponseEntity.ok().body(authenticationService.obterUsuarioLogadoNoSistema());
    }

    @PostMapping("cadastrarUsuario")
    public ResponseEntity<UsersResponse> cadastrarUsuario(@Valid @RequestBody UsersRequest usersRequest){
        return  ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.cadastrarUsuario(usersRequest));
    }

    @GetMapping("usuario/{id}")
    public ResponseEntity<UsersResponse> obterUsuario(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(authenticationService.obterUsuario(id));
    }

    @PutMapping("atualizarUsuario/{id}")
    public ResponseEntity<UsersResponse> atualizarUsuario(@RequestBody UsersRequest usersRequest, @PathVariable("id") Long id) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.atualizarUsuario(usersRequest, id));
    }

    @DeleteMapping("removerUsuario/{id}")
    public ResponseEntity<UsersResponse> removerUsuario(@PathVariable Long id){
        UsersResponse usersResponse = authenticationService.obterUsuario(id);
        authenticationService.removerUsuario(usersResponse);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(usersResponse);
    }

}
