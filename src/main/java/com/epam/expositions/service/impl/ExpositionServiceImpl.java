package com.epam.expositions.service.impl;

import com.epam.expositions.dao.ExpositionDAO;
import com.epam.expositions.dao.impl.ExpositionDAOImpl;
import com.epam.expositions.dto.ExpositionDTO;
import com.epam.expositions.entity.Exposition;
import com.epam.expositions.service.ExpositionService;
import com.epam.expositions.util.Converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.epam.expositions.util.Converter.convertLocalDateTimeToSQLDateString;

public class ExpositionServiceImpl implements ExpositionService {
    private final ExpositionDAO expositionDAO = new ExpositionDAOImpl();

    @Override
    public Exposition findById(Long id) {
        return null;
    }

    @Override
    public List<ExpositionDTO> findByDate(LocalDateTime date) {
        List<Exposition> expositions = expositionDAO.findByDate(date);
        List<ExpositionDTO> expositionDTOS = new ArrayList<>();
        for (Exposition e :
                expositions) {
            expositionDTOS.add(ExpositionDTO.builder()
                    .topic(e.getTopic())
                    .dateStart(convertLocalDateTimeToSQLDateString(e.getDateStart()))
                    .dateEnd(convertLocalDateTimeToSQLDateString(e.getDateEnd()))
                    .price(e.getPrice())
                    .capacity(e.getCapacity())
                    .imagePath(e.getImagePath())
                    .detailsLink(e.getDetailsLink())
                    .build());
        }
        return expositionDTOS;
    }

    @Override
    public Exposition findByHostId(String email) {
        return null;
    }

    @Override
    public List<ExpositionDTO> findALL(boolean showLegacy) {
        List<ExpositionDTO> expositionDTOS = new ArrayList<>();
        List<Exposition> expositions = expositionDAO.findALL();
        for (Exposition item :
                expositions) {

            if (!showLegacy && item.getDateEnd().isAfter(LocalDateTime.now())) {
                expositionDTOS.add(ExpositionDTO.builder()
                        .topic(item.getTopic())
                        .dateStart(Converter.parseExpositionDateToDTOString(item.getDateStart()))
                        .dateEnd(Converter.parseExpositionDateToDTOString(item.getDateEnd()))
                        .price(item.getPrice())
                        .capacity(item.getCapacity())
                        .imagePath(item.getImagePath())
                        .detailsLink(item.getDetailsLink())
                        .build());
            }

            if (showLegacy) {
                expositionDTOS.add(ExpositionDTO.builder()
                        .topic(item.getTopic())
                        .dateStart(Converter.parseExpositionDateToDTOString(item.getDateStart()))
                        .dateEnd(Converter.parseExpositionDateToDTOString(item.getDateEnd()))
                        .price(item.getPrice())
                        .capacity(item.getCapacity())
                        .imagePath(item.getImagePath())
                        .detailsLink(item.getDetailsLink())
                        .build());
            }

        }
        return expositionDTOS;
    }

    @Override
    public Exposition create(Exposition entity) {
        return null;
    }

    @Override
    public Exposition update(Exposition entity, Long id) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean delete(Exposition entity) {
        return false;
    }
}