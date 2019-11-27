package com.isil.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vehicle {
    private Long idVehicle;
    private String licensePlate;
    private String brand;
    private String model;
    private String color;
    private Integer capacity;
    private Long idDriver;

    public Vehicle(String license_plate, String brand, String model, String color, Integer capacity, Long idDriver) {
        this.licensePlate = license_plate;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.capacity = capacity;
        this.idDriver = idDriver;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "idVehicle=" + idVehicle +
                ", license_plate='" + licensePlate + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", capacity=" + capacity +
                ", idDriver=" + idDriver +
                '}';
    }
}
