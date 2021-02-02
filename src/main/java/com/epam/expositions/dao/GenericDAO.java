package com.epam.expositions.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO <T, ID>{

    Optional<T> findById(ID id);

    List<Optional<T>> findALL();

    Optional<T> create(T entity);

    Optional<T> update(T entity, ID id);

    boolean deleteById(ID id);

    boolean delete(T entity);

}
