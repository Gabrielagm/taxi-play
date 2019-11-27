package com.isil.service;

import com.isil.dao.DriverDAO;
import com.isil.model.Driver;

import java.sql.SQLException;
import java.util.List;

public class DriverService {
    static DriverDAO driverDAO = new DriverDAO();

    public void create(Driver driver) throws SQLException {
        driverDAO.create(driver);
    }

    public static Driver findOne(Long id) {

        return driverDAO.findById(id);
    }

    public List<Driver> findAll() {

        return driverDAO.findAll();
    }

    public boolean delete(Long id) throws SQLException {
        return driverDAO.delete(id);
    }

    public boolean update(Driver driver) throws SQLException {
        return driverDAO.update(driver);
    }
}
