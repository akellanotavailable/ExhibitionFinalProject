package com.epam.expositions.service;

import com.epam.expositions.entity.User;

import java.util.List;

public interface UserService{
    User findById(Long id);

    User findByLogin(String login);

    List<User> findALL();

    User create(User entity);

    User update(User entity, Long id);

    boolean deleteById(Long id);

    boolean delete(User entity);
}
