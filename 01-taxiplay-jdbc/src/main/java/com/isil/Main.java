package com.isil;

import com.isil.dao.DriverDAO;
import com.isil.dao.ReservationDAO;
import com.isil.model.Driver;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //delete();
        //create("Alejandro","Figeroa","28884444","945672222","alejo@gmail.com");
        //findAll();
        //findById();
        //update("Alejandro","Figeroa","22222222","777777777","alejofigeroa@gmail.com");

    }

    static DriverDAO driverDAO = new DriverDAO();
    static List<Driver> drivers = driverDAO.findAll();
    static Driver driver = new Driver();
    static ReservationDAO reservationDAO = new ReservationDAO();
    static Scanner sc = new Scanner(System.in);

    public static void delete (){
        System.out.println("Ingrese el ID del registro a eliminar: \n");
        Long st = sc.nextLong();
        driverDAO.delete(st);
    }

    public static void create(String name, String lastName, String dni, String phone, String email){
        driver = new Driver(name,lastName,dni,phone,email);
        driverDAO.create(driver);
        System.out.println("Datos del chofer:  "+driver.toString());
    }

    public static void findAll(){
        System.out.println("**  Estos son todos los choferes registrados  **");
        drivers.stream().forEach(u -> System.out.println("Datos de Chofer : "+u));
    }

    public static void findById(){
        System.out.println("Ingrese el ID del chofer que busca: \n");
        Long st = sc.nextLong();
        driverDAO.findById(st);
    }

    public static void update(String name, String lastName, String dni, String phone, String email) {
        System.out.println("Ingrese el ID del chofer para actualizar sus datos: \n");
        Long st = sc.nextLong();
        driver = driverDAO.findById(st);
        //driver = new Driver(name, lastName, dni, phone, email);
        driverDAO.update(driver);
    }
}
