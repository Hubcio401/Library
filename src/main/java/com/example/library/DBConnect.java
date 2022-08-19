package com.example.library;

import java.sql.*;

public class DBConnect {

    private final static String DBURL = "jdbc:postgresql://127.0.0.1:5432/library";
    private final static String DBUSER = "postgres";
    private final static String DBPASS = "123";

    public static Connection connect(){

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
            System.out.println("Connect");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
