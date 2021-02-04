package com.epam.expositions.service.impl;

import com.epam.expositions.dto.RegisterDTO;
import com.epam.expositions.entity.Role;
import com.epam.expositions.entity.RoleName;
import com.epam.expositions.entity.User;
import com.epam.expositions.exception.InvalidDataException;
import com.epam.expositions.exception.UserNotFoundException;
import com.epam.expositions.service.RegisterService;
import com.epam.expositions.service.UserService;

public class RegisterServiceImpl implements RegisterService {
    private final UserService userService = new UserServiceImpl();

    @Override
    public void register(RegisterDTO data) throws InvalidDataException {

        if (!data.getPassword().equals(data.getRePassword())) {
            throw new InvalidDataException("Passwords do not match.");
        }

        boolean isUserExists = true;
        boolean isEmailExists = true;

        try {
            userService.findByLogin(data.getLogin());
        } catch (UserNotFoundException exception) {
            isUserExists = false;
        }
        try {
            userService.findByEmail(data.getEmail());
        } catch (UserNotFoundException exception) {
            isEmailExists = false;
        }

        if (isUserExists) {
            throw new InvalidDataException("This user already exists");
        }

        if (isEmailExists) {
            throw new InvalidDataException("This email is already registered.");
        }

        userService.create(User.builder()
                .login(data.getLogin())
                .email(data.getEmail())
                .password(data.getPassword())
                .role(new Role(2L, RoleName.USER.getRoleName()))
                .build());

    }
}
