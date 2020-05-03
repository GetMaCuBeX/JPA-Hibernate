package com.mcbx.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {

    public static void main(String[] args) throws Exception {
        String jdbcUrl = "jdbc:mysql://localhost:3306/booksreservation?useSSL=false";
        String user = "root";
        String pass = "";
        System.out.println("Connecting to database: " + jdbcUrl);
        Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
        System.out.println("Connection successful!!!");
    }

}
