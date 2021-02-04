package com.epam.expositions.dao.impl;

import com.epam.expositions.dao.UserDAO;
import com.epam.expositions.entity.Role;
import com.epam.expositions.entity.User;
import com.epam.expositions.util.ConnectionDB;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static com.epam.expositions.query.UserQueries.*;
import static com.epam.expositions.query.UtilQueries.SELECT_LAST_INSERTED_ID;

public class UserDAOImpl implements UserDAO {

    private final Connection connection = ConnectionDB.getConnection();


    @Override
    @SneakyThrows
    public Optional<User> findById(Long id) {

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USER_BY_ID);
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getLong("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    new Role(resultSet.getLong("role_id"),
                           resultSet.getString("name")));

        }

        return Optional.ofNullable(user);

    }

    @Override
    public List<Optional<User>> findALL() {
        return null;
    }

    @Override
    @SneakyThrows
    public Optional<User> create(User entity) {
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER);
        preparedStatement.setString(1, entity.getLogin());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setString(3, entity.getEmail());
        preparedStatement.setLong(4, entity.getRole().getId());
        preparedStatement.executeUpdate();

        ResultSet resultSet = connection
                .prepareStatement(SELECT_LAST_INSERTED_ID)
                .executeQuery();
        resultSet.next();
        return findById(resultSet.getLong(1));
    }

    @Override
    public Optional<User>  update(User entity, Long id) {
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

    @Override
    @SneakyThrows
    public Optional<User> findByLogin(String login) {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USER_BY_LOGIN);
        preparedStatement.setString(1, login);

        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getLong("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    new Role(resultSet.getLong("role_id"),
                            resultSet.getString("name")));

        }

        return Optional.ofNullable(user);
    }

    @Override
    @SneakyThrows
    public Optional<User> findByEmail(String email) {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USER_BY_EMAIL);
        preparedStatement.setString(1, email);

        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getLong("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    new Role(resultSet.getLong("role_id"),
                            resultSet.getString("name")));

        }

        return Optional.ofNullable(user);
    }
}
