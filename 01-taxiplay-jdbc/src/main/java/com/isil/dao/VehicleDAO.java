package com.isil.dao;

import com.isil.model.Vehicle;
import com.isil.util.Constants;
import com.isil.util.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO implements DAO<Vehicle, Long> {


    @Override
    public void create(Vehicle vehicle) {
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(Constants.INSERT_VEHICLE, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, vehicle.getLicensePlate());
                statement.setString(2, vehicle.getBrand());
                statement.setString(3, vehicle.getModel());
                statement.setString(4, vehicle.getColor());
                statement.setInt(5, vehicle.getCapacity());
                statement.setLong(6, vehicle.getIdDriver());
                if (statement.executeUpdate() == 0){
                    System.out.println("No se pudo insertar!!!");
                }else{
                    System.out.println("Acabas de insertar un registro!!!");
                }
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()){
                    vehicle.setIdVehicle(rs.getLong(1));
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
            try (PreparedStatement statement = connection.prepareStatement(Constants.DELETE_VEHICLE)) {
                statement.setLong(1, id);
                if (statement.executeUpdate() == 0) {
                    System.out.println("No se pudo eliminar!!!");
                }else{
                    System.out.println("Acabas de eliminar un registro!");
                }
            }

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return  rowDeleted;
    }

    @Override
    public boolean update(Vehicle vehicle) {
        boolean rowUpdated = false;
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_VEHICLE)) {
                statement.setString(1, vehicle.getLicensePlate());
                statement.setString(2, vehicle.getBrand());
                statement.setString(3, vehicle.getModel());
                statement.setString(4, vehicle.getColor());
                statement.setInt(5, vehicle.getCapacity());
                statement.setLong(6, vehicle.getIdDriver());
                statement.setLong(7, vehicle.getIdVehicle());
                if (statement.executeUpdate() == 0) {
                    System.out.println("No se pudo modificar porque no existe el ID!!!");
                }else{
                    System.out.println("Acabas de actualizar los datos de este chofer");
                    System.out.println(vehicle.toString());
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return rowUpdated;
    }

    @Override
    public Vehicle findById(Long id) {
        Vehicle vehicle = null;
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(Constants.GETBYID_VEHICLE)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        vehicle = throughVehicle(resultSet);
                        System.out.println("Estos son los datos del Chofer: ");
                        System.out.println(vehicle.toString());
                    }else{
                        System.out.println("El Id que ingresó no se encuentra en el registro");
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        return vehicle;
    }

    @Override
    public List<Vehicle> findAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(Constants.GETALL_VEHICLE)) {
                    while (resultSet.next()) {
                        vehicles.add(throughVehicle(resultSet));
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return vehicles;
    }


    public Vehicle throughVehicle(ResultSet rs) throws SQLException {
        String licensePlate = rs.getString("license_plate");
        String brand = rs.getString("brand");
        String model = rs.getString("model");
        String color = rs.getString("color");
        Integer capacity = rs.getInt("capacity");
        Long idDriver = rs.getLong("id_driver");
        Long idVehicle = rs.getLong("id_vehicle");
        Vehicle vehicle = new Vehicle(idVehicle, licensePlate, brand, model, color, capacity, idDriver);
        return vehicle;
    }

}
