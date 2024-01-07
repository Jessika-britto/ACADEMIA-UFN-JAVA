package com.app.appbackend.model.response;

import com.app.appbackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}
