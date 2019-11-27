package com.isil.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtil {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(Constants.DRIVER);
        return DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
    }

}
