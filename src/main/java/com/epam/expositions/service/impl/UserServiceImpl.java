package com.epam.expositions.service.impl;

import com.epam.expositions.dao.UserDAO;
import com.epam.expositions.dao.impl.UserDAOImpl;
import com.epam.expositions.entity.User;
import com.epam.expositions.exception.UserNotFoundException;
import com.epam.expositions.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User findByLogin(String login) {
        return userDAO.getByLogin(login).orElseThrow(() -> new UserNotFoundException("User not found - " + login));
    }

    @Override
    public List<User> findALL() {
        return null;
    }

    @Override
    public User create(User entity) {
        return null;
    }

    @Override
    public User update(User entity, Long id) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }
}
