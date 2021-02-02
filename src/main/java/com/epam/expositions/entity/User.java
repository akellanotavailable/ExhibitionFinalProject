package com.epam.expositions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String login;
    private String password;
    private String email;
    private Role role;
}
