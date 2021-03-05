package com.epam.expositions.dao.impl;

import com.epam.expositions.dao.AbstractDAO;
import com.epam.expositions.dao.HallDAO;
import com.epam.expositions.dao.mapper.HallMapper;
import com.epam.expositions.dao.query.HallQueries;
import com.epam.expositions.dto.HallExpositionDTO;
import com.epam.expositions.dto.HallTimetableDTO;
import com.epam.expositions.entity.Hall;
import com.epam.expositions.util.ConnectionDB;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HallDAOImpl extends AbstractDAO<Hall, Long> implements HallDAO {
    private final Connection connection = ConnectionDB.getConnection();

    public HallDAOImpl() {
        super(ConnectionDB.getConnection(), new HallMapper());
    }

    @Override
    protected String getSelectQuery() {
        return HallQueries.SELECT_FROM_HALL;
    }

    @Override
    protected String getSelectByIdQuery() {
        return HallQueries.SELECT_FROM_HALL_BY_ID;
    }

    @Override
    protected String getSelectLastInsertedQuery() {
        return HallQueries.SELECT_FROM_HALL_LAST;
    }

    @Override
    protected String getCreateQuery() {
        return HallQueries.CREATE_HALL;
    }

    @Override
    protected String getUpdateQuery() {
        return HallQueries.UPDATE_HALL_BY_ID;
    }

    @Override
    protected String getDeleteQuery() {
        return HallQueries.DELETE_HALL;
    }

    @Override
    @SneakyThrows
    protected void prepareInsertStatement(PreparedStatement statement, Hall object) {
        statement.setLong(1, object.getId());
        statement.setString(2, object.getName());
    }

    @Override
    @SneakyThrows
    protected void prepareUpdateStatement(PreparedStatement statement, Hall object) {
        statement.setLong(1, object.getId());
        statement.setString(2, object.getName());
        statement.setLong(3, object.getId());
    }

    @SneakyThrows
    public List<HallExpositionDTO> getHallExpositionReservation() {
        List<HallExpositionDTO> list = new ArrayList<>();

        PreparedStatement statement = connection
                .prepareStatement("SELECT hall_id, exposition_id FROM hall_has_exposition");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            list.add(
                    new HallExpositionDTO(
                            resultSet.getLong("hall_id"),
                            resultSet.getLong("exposition_id")));
        }

        return list;
    }
}
