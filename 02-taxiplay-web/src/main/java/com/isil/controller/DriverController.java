package com.isil.controller;

import com.isil.model.Driver;
import com.isil.service.DriverService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class DriverController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DriverService driverService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        driverService = new DriverService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getServletPath();
        try {
            switch (mode){
                case "/new":
                    newForm(request, response);
                    break;
                case "/insert":
                    insertDriver(request,response);
                    break;
                case "/delete":
                    deleteDriver(request,response);
                    break;
                case "/edit":
                    editDriver(request,response);
                    break;
                case "/update":
                    updateDriver(request,response);
                    break;
                default:
                    driverList(request,response);
                    break;
            }

        }catch (SQLException e){
            throw new ServletException(e);
        }
    }

    private void newForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("driverForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertDriver(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String dni = request.getParameter("dni");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        Driver newDriver = new Driver(nombres, apellidos, dni, telefono, correo);
        driverService.create(newDriver);
        response.sendRedirect("list");
    }

    private void deleteDriver(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        driverService.delete(id);
        response.sendRedirect("list");
    }

    private void editDriver(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Driver driverFind = driverService.findOne(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("driverForm.jsp");
        request.setAttribute("driverFind", driverFind);
        dispatcher.forward(request, response);
    }

    private void updateDriver(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String dni = request.getParameter("dni");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        Driver driverModif = new Driver(id, nombres, apellidos, dni, telefono, correo);
        driverService.update(driverModif);
        response.sendRedirect("list");
    }

    private void driverList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Driver> driverList = driverService.findAll();
        request.setAttribute("driverList", driverList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("driverMaintenance.jsp");
        dispatcher.forward(request, response);
    }
}
