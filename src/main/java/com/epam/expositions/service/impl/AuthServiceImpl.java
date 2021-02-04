package com.epam.expositions.service.impl;

import com.epam.expositions.dto.LoginDTO;
import com.epam.expositions.entity.User;
import com.epam.expositions.exception.InvalidDataException;
import com.epam.expositions.exception.UserNotFoundException;
import com.epam.expositions.service.AuthService;
import com.epam.expositions.service.UserService;

public class AuthServiceImpl implements AuthService {

    private final UserService userService = new UserServiceImpl();

    @Override
    public void authenticate(LoginDTO data) throws InvalidDataException {

        if (data.getLogin() == null || data.getPassword() == null || data.getLogin().isBlank() || data.getPassword().isBlank()) {
            throw new InvalidDataException("Unexpected data input.");
        }

        try {
            User user = userService.findByLogin(data.getLogin());
            if (!user.getPassword().equals(data.getPassword())) {
                throw new InvalidDataException("Wrong password.");
            }
        } catch (UserNotFoundException e) {
            throw new InvalidDataException("No such login.");
        }

    }
}
