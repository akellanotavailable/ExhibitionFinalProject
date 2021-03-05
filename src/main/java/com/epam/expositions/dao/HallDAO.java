package com.epam.expositions.dao;

import com.epam.expositions.dto.HallExpositionDTO;
import com.epam.expositions.entity.Hall;

import java.util.List;

public interface HallDAO extends GenericDAO<Hall, Long>{
    public List<HallExpositionDTO> getHallExpositionReservation();
}
