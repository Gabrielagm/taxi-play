package com.isil.dao;

import com.isil.model.Passenger;
import com.isil.util.Constants;
import com.isil.util.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAO implements  DAO<Passenger, Long>{
    @Override
    public void create(Passenger passenger) {
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(Constants.INSERT_PASSENGER, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, passenger.getName());
                statement.setString(2, passenger.getLastName());
                statement.setString(3, passenger.getPhone());
                statement.setString(4, passenger.getEmail());
                statement.setString(5, passenger.getPassword());
                if (statement.executeUpdate() == 0){
                    System.out.println("No se pudo insertar!!!");
                }else{
                    System.out.println("Acabas de insertar un registro!!!");
                }
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()){
                    passenger.setIdPassenger(rs.getLong(1));
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
            try (PreparedStatement statement = connection.prepareStatement(Constants.DELETE_PASSENGER)) {
                statement.setLong(1, id);
                statement.executeUpdate();
                if (statement.executeUpdate() == 0) {
                    System.out.println("No se pudo eliminar el registro porque el Id no está registrado!!!");
                }else{
                    System.out.println("Acabas de eliminar un registro!");
                }
            }

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Passenger passenger) {
        boolean rowUpdated = false;
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_PASSENGER)) {
                statement.setString(1, passenger.getName());
                statement.setString(2, passenger.getLastName());
                statement.setString(3, passenger.getPhone());
                statement.setString(4, passenger.getEmail());
                statement.setString(5, passenger.getPassword());
                statement.setLong(6, passenger.getIdPassenger());
                if (statement.executeUpdate() == 0) {
                    System.out.println("No se pudo modificar porque no existe el ID!!!");
                }else{
                    System.out.println("Acabas de actualizar los datos de este chofer");
                    System.out.println(passenger.toString());
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return rowUpdated;
    }

    @Override
    public Passenger findById(Long id) {
        Passenger passenger = null;
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(Constants.GETBYID_PASSENGER)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        passenger = througPassenger(resultSet);
                        System.out.println("Estos son los datos del Pasajero: ");
                        System.out.println(passenger.toString());
                    }else{
                        System.out.println("El Id que ingresó no se encuentra en el registro");
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        return passenger;
    }

    @Override
    public List<Passenger> findAll() {
        List<Passenger> passengers = new ArrayList<>();
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(Constants.GETALL_PASSENGER)) {
                    while (resultSet.next()) {
                        passengers.add(througPassenger(resultSet));
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return passengers;
    }

    public Passenger througPassenger(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String lastName = rs.getString("last_name");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String password = rs.getString("password");
        Long idPassenger = rs.getLong("id_passenger");
        Passenger passenger = new Passenger(idPassenger, name, lastName, phone, email, password);
        return passenger;
    }
}
