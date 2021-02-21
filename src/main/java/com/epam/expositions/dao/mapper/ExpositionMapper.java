package com.epam.expositions.dao.mapper;

import com.epam.expositions.entity.Exposition;
import com.epam.expositions.util.Converter;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExpositionMapper implements ResultSetMapper<Exposition> {
    @Override
    @SneakyThrows
    public List<Exposition> map(ResultSet resultSet) {
        List<Exposition> expositions = new ArrayList<>();
        while (resultSet.next()) {
            Exposition exposition = Exposition.builder()
                    .id(resultSet.getLong("id"))
                    .hostId(resultSet.getLong("host_user_id"))
                    .topic(resultSet.getString("topic"))
                    .dateStart(resultSet.getObject("date_start", LocalDateTime.class))
                    .dateEnd(resultSet.getObject("date_end", LocalDateTime.class))
                    .capacity(resultSet.getLong("capacity"))
                    .price(resultSet.getBigDecimal("price"))
                    .imagePath(resultSet.getString("image"))
                    .detailsLink(resultSet.getString("details"))
                    .build();
            expositions.add(exposition);
        }
        return expositions;
    }
}
