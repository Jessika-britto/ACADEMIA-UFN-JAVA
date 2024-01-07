package com.app.appbackend.model.response;

import com.app.appbackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoggedResponse {
    private Long id;
    private String userLogged;
    private List<String> permissions;
}
