package com.isil.dao;

import com.isil.model.Reservation;
import com.isil.model.ReservationKey;
import com.isil.util.Constants;
import com.isil.util.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationDAO implements  DAO<Reservation, Long>{
    @Override
    public void create(Reservation reservation) {
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(Constants.INSERT_RESERVATION, Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, reservation.getReservationKey().getIdPassenger());
                statement.setLong(2,reservation.getReservationKey().getIdVehicle());
                statement.setString(3, reservation.getStartingAddress());
                statement.setString(4, reservation.getArrivalAddress());
                statement.setDouble(5,reservation.getDistance());
                statement.setDouble(6,reservation.getTotalToPay());
                statement.setString(7,reservation.getPaymentMethod());

                if (statement.executeUpdate() == 0){
                    System.out.println("No se pudo insertar!!!");
                }else{
                    System.out.println("Acabas de insertar un registro!!!");
                }

                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()){
                    reservation.setIdReservation(rs.getLong(1));
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
            try (PreparedStatement statement = connection.prepareStatement(Constants.DELETE_RESERVATION)) {
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
        return rowDeleted;
    }

    @Override
    public boolean update(Reservation reservation) {
        boolean rowUpdated = false;
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_RESERVATION)) {
                statement.setLong(1,reservation.getReservationKey().getIdPassenger());
                statement.setLong(2,reservation.getReservationKey().getIdVehicle());
                statement.setString(3, reservation.getStartingAddress());
                statement.setString(4, reservation.getArrivalAddress());
                statement.setString(5, reservation.getPaymentMethod());
                statement.setLong(6, reservation.getIdReservation());
                if (statement.executeUpdate() == 0) {
                    System.out.println("No se pudo modificar porque no existe el ID!!!");
                }else{
                    System.out.println("Acabas de actualizar los datos de este chofer");
                    System.out.println(reservation.toString());
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return rowUpdated;
    }

    @Override
    public Reservation findById(Long id) {
        Reservation reservation = null;
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(Constants.GETBYID_RESERVATION)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        reservation = throughReservation(resultSet);
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return  reservation;
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection connection = DataBaseUtil.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(Constants.GETALL_RESERVATION)) {
                    while (resultSet.next()) {
                        reservations.add(throughReservation(resultSet));
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return reservations;
    }

    public Reservation throughReservation(ResultSet rs) throws SQLException {
        Long idReservation = rs.getLong("id_reservation");
        ReservationKey reservationKeyPassenger = (ReservationKey) rs.getArray("id_passenger");
        ReservationKey reservationKeyVehicle = (ReservationKey) rs.getArray("id_vehicle");
        String startingAddress = rs.getString("starting_address");
        String arrivalAddress = rs.getString("arrival_address");
        Double distance = rs.getDouble("distance");
        Double totalToPay = rs.getDouble("total_to_pay");
        String paymentMethod = rs.getString("payment_method");

        Reservation reservation = new Reservation(idReservation, reservationKeyPassenger, reservationKeyVehicle,startingAddress, arrivalAddress, distance, totalToPay, paymentMethod);
        return reservation;
    }

}
