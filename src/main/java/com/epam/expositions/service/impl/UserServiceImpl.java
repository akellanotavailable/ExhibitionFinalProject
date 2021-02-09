package com.epam.expositions.service.impl;

import com.epam.expositions.dao.UserDAO;
import com.epam.expositions.dao.impl.UserDAOImpl;
import com.epam.expositions.entity.User;
import com.epam.expositions.exception.InvalidDataException;
import com.epam.expositions.exception.UserNotFoundException;
import com.epam.expositions.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User findByLogin(String login) {
        return userDAO.findByLogin(login).orElseThrow(() -> new UserNotFoundException("User not found - " + login));
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found - " + email));
    }

    @Override
    public List<User> findALL() {
        return userDAO.findALL();
    }

    @Override
    public User create(User entity) {
        return userDAO.create(entity);
    }

    @Override
    public User update(User entity, Long id) {
        return userDAO.update(entity, id);
    }

    @Override
    public boolean deleteById(Long id) {
        return userDAO.deleteById(id);
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }
}
