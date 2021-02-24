package com.epam.expositions.service.impl;

import com.epam.expositions.dao.PurchaseDAO;
import com.epam.expositions.dao.impl.PurchaseDAOImpl;
import com.epam.expositions.entity.Purchase;
import com.epam.expositions.service.PurchaseService;

import java.util.List;

public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseDAO purchaseDAO = new PurchaseDAOImpl();

    @Override
    public List<Purchase> findByExpositionId(Long id) {
        return purchaseDAO.findByExpositionId(id);
    }

    @Override
    public List<Purchase> findByUserId(Long id) {
        return purchaseDAO.findByUserId(id);
    }

    @Override
    public List<Purchase> findALL() {
        return purchaseDAO.findALL();
    }

    @Override
    public Purchase create(Purchase entity) {
        return purchaseDAO.create(entity);
    }

    @Override
    public Purchase update(Purchase entity, Long id) {
        return purchaseDAO.update(entity, id);
    }

    @Override
    public boolean deleteByExpositionId(Long id) {
        return purchaseDAO.deleteByExpositionId(id);
    }

    @Override
    public boolean deleteByUserId(Long id) {
        return purchaseDAO.deleteByUserId(id);
    }

    @Override
    public boolean delete(Purchase entity) {
        return purchaseDAO.delete(entity);
    }
}
