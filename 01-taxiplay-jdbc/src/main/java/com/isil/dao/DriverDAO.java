package com.isil.dao;

import com.isil.model.Driver;
import com.isil.util.Constants;
import com.isil.util.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO implements DAO<Driver, Long> {
    @Override
    public void create(Driver driver) {
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(Constants.INSERT_DRIVER, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, driver.getName());
                statement.setString(2, driver.getLastName());
                statement.setString(3, driver.getDni());
                statement.setString(4, driver.getPhone());
                statement.setString(5, driver.getEmail());

                if (statement.executeUpdate() == 0){
                    System.out.println("No se pudo insertar!!!");
                }else{
                    System.out.println("Acabas de insertar un registro!!!");
                }
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()){
                    driver.setIdDriver(rs.getLong(1));
                }

            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public boolean delete(Long id) {
        boolean rowDeleted = false;
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(Constants.DELETE_DRIVER)) {
                statement.setLong(1, id);
                rowDeleted = statement.executeUpdate() > 0;
            }

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Driver driver) {
        boolean rowUpdate = false;
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_DRIVER)) {
                statement.setString(1, driver.getName());
                statement.setString(2, driver.getLastName());
                statement.setString(3, driver.getDni());
                statement.setString(4, driver.getPhone());
                statement.setString(5, driver.getEmail());
                statement.setLong(6, driver.getIdDriver());
                rowUpdate = statement.executeUpdate() > 0;
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return rowUpdate;
    }

    @Override
    public Driver findById(Long id) {
        Driver driver = null;
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(Constants.GETBYID_DRIVER)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        driver = throughDriver(resultSet);
                        System.out.println("Estos son los datos del Chofer: ");
                        System.out.println(driver.toString());
                    }else{
                        System.out.println("El Id que ingresó no se encuentra en el registro");
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        return driver;
    }

    @Override
    public List<Driver> findAll() {
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(Constants.GETALL_DRIVER)) {
                    while (resultSet.next()) {
                        drivers.add(throughDriver(resultSet));
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return drivers;
    }


    public Driver throughDriver(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String lastName = rs.getString("last_name");
        String dni = rs.getString("dni");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        Long idDriver = rs.getLong("id_driver");
        Driver driver = new Driver(idDriver, name, lastName, dni, phone, email);
        return driver;
    }

}
