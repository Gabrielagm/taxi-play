package com.isil.util;

public class Constants {

    //DB ConexiónisErrorPage="true"
    public static final String URL = "jdbc:mysql://mysql02.c4utrkzk5zgf.us-west-1.rds.amazonaws.com:3306/isil_db";
    public static final String USER = "isil";
    public static final String PASSWORD = "isil";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    //DriverDAO
    public static final String INSERT_DRIVER = "insert into driver(name, last_name, dni, phone, email) values (?,?,?,?,?)";
    public static final String UPDATE_DRIVER = "update driver set name=?, last_name=?, dni=?, phone=?, email=? where id_driver=?";
    public static final String DELETE_DRIVER = "delete from driver where id_driver=?";
    public static final String GETALL_DRIVER = "select id_driver, name, last_name, dni, phone, email from driver";
    public static final String GETBYID_DRIVER = "select id_driver, name, last_name, dni, phone, email from driver where id_driver=?";

    //PassengerDAO
    public static final String INSERT_PASSENGER = "insert into passenger(name, last_name, phone, email, password) values (?,?,?,?,?)";
    public static final String UPDATE_PASSENGER = "update passenger set name=?, last_name=?, phone=?, email=?, password=? where id_passenger=?";
    public static final String DELETE_PASSENGER = "delete from passenger where id_passenger=?";
    public static final String GETALL_PASSENGER = "select id_passenger, name, last_name, phone, email, password from passenger";
    public static final String GETBYID_PASSENGER = "select id_passenger, name, Last_name, phone, email, password from passenger where id_passenger=?";

    //VehicleDAO
    public static final String INSERT_VEHICLE = "insert into vehicle(license_plate, brand, model, color, capacity, id_driver) values (?,?,?,?,?,?)";
    public static final String UPDATE_VEHICLE = "update vehicle set license_plate=?, brand=?, model=?, color=?, capacity=? id_driver=? where id_vehicle=?";
    public static final String DELETE_VEHICLE = "delete from vehicle where id_vehicle=?";
    public static final String GETALL_VEHICLE = "select id_vehicle, license_plate, brand, model, color, capacity, id_driver from vehicle";
    public static final String GETBYID_VEHICLE = "select id_vehicle, license_plate, marca, model, color, capacity, id_driver from vehicle where id_vehicle=?";

    //ReservationDAO
    public static final String INSERT_RESERVATION = "insert into reservation(id_passenger, id_vehicle, \n" +
            "starting_address, arrival_address,distance,total_to_pay, payment_method) values (?,?,?,?,?,?,?)";
    public static final String UPDATE_RESERVATION = "update reservation set id_passenger=?,id_vehicle=?, starting_address=?, arrival_address=?,payment_method=? where id_reservation=?";
    public static final String DELETE_RESERVATION = "delete from reservation where id_reservation=?";
    public static final String GETALL_RESERVATION = "select * from reservation";
    public static final String GETBYID_RESERVATION = "select * from reservation where id_reservation=?";

}
