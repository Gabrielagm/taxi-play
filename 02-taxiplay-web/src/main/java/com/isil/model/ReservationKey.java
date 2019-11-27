package com.isil.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationKey {
    private Long idPassenger;
    private Long idVehicle;

    @Override
    public String toString() {
        return "ReservationID{" +
                "idPassenger=" + idPassenger +
                ", idVehicle=" + idVehicle +
                '}';
    }
}
