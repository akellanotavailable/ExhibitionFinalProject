package com.epam.expositions.dao.impl;

import com.epam.expositions.dao.UserDAO;
import com.epam.expositions.entity.Role;
import com.epam.expositions.entity.RoleName;
import com.epam.expositions.entity.User;
import com.epam.expositions.query.UserQueries;
import com.epam.expositions.util.ConnectionDB;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static com.epam.expositions.query.UserQueries.SELECT_FROM_USER_BY_ID;
import static com.epam.expositions.query.UserQueries.SELECT_FROM_USER_BY_LOGIN;

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
    public Optional<User>  create(User entity) {
        return null;
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
    public Optional<User> getByLogin(String login) {
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

}
