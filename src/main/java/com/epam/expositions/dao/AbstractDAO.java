package com.epam.expositions.dao;

import com.epam.expositions.dao.mapper.ResultSetMapper;
import com.epam.expositions.dao.query.UtilQueries;
import com.epam.expositions.entity.Persistable;
import com.epam.expositions.exception.UserNotFoundException;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T extends Persistable<ID>, ID> implements GenericDAO<T, ID> {

    private final Connection connection;
    protected ResultSetMapper<T> mapper;

    public AbstractDAO(Connection connection, ResultSetMapper<T> mapper) {
        this.connection = connection;
        this.mapper = mapper;
    }

    protected abstract String getSelectQuery();

    protected abstract String getSelectByIdQuery();

    protected abstract String getSelectLastInsertedQuery();

    protected abstract String getCreateQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract void prepareInsertStatement(PreparedStatement statement, T object);

    protected abstract void prepareUpdateStatement(PreparedStatement statement, T object);

    private static <ID> ID convertInstanceOfObject(Object o, Class<ID> templateClass) {
        try {
            return templateClass.cast(o);
        } catch (ClassCastException e) {
            return null;
        }
    }

    @Override
    @SneakyThrows
    public Optional<T> findById(ID id) {
        String query = getSelectByIdQuery();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, id.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<T> res = mapper.map(resultSet);
        if (res.size() > 1) {
            throw new SQLException("Received more than one record.");
        }
        return res.stream().findFirst();
    }

    @Override
    @SneakyThrows
    public List<T> findALL() {
        String query = getSelectQuery();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return mapper.map(resultSet);
    }

    @Override
    @SneakyThrows
    public Optional<T> create(T entity) {
        PreparedStatement preparedStatement = connection.prepareStatement(getCreateQuery());
        prepareInsertStatement(preparedStatement, entity);
        preparedStatement.executeUpdate();

        ResultSet resultSet = connection
                .prepareStatement(getSelectLastInsertedQuery())
                .executeQuery();

        T newUser = mapper.map(resultSet).stream().findFirst().orElseThrow(() ->
                new UserNotFoundException("Unable to create a new user with data provided."));
        return findById(newUser.getId());
    }

    @Override
    public Optional<T> update(T entity, ID id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(ID id) {
        return false;
    }

    @Override
    public boolean delete(T entity) {
        return false;
    }
}
