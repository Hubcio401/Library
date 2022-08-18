package com.example.library;

import java.sql.*;

public class DBConnect {

    private final static String DBURL = "jdbc:postgresql://127.0.0.1:5432/library";
    private final static String DBUSER = "postgres";
    private final static String DBPASS = "123";
//    private final static String DBDRIVER = "com.mysql.jdbc.Driver";

    public static Connection connect(){

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
            System.out.println("Połączono");


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
