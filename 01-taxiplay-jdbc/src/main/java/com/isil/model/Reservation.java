package com.isil.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
    private Long idReservation;
    private ReservationKey reservationKey;
    private String startingAddress;
    private String arrivalAddress;
    private Double distance;
    private Double totalToPay;
    private String paymentMethod;

    public Reservation(Long idReservation, ReservationKey reservationKeyPassenger, ReservationKey reservationKeyVehicle, String startingAddress, String arrivalAddress, Double distance, Double totalToPay, String paymentMethod) {
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", reservationKey=" + reservationKey +
                ", startingAddress='" + startingAddress + '\'' +
                ", arrivalAddress='" + arrivalAddress + '\'' +
                ", distance=" + distance +
                ", totalToPay=" + totalToPay +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
