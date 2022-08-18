package com.example.library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB_add_to_table {

    public static ArrayList<Books> books_list = new ArrayList<>();

    public static void add(){

        Connection conn = DBConnect.connect();

        Statement stmt = null;

        try {
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery( "select * from krk_library ;" );

            while ( rs.next() ) {

                String title = rs.getString("Title");

                String author = rs.getString("Author");

                String genre  = rs.getString("Genre");

                int height  = rs.getInt("Height");

                String publisher  = rs.getString("Publisher");

                Books obj = new Books(title,author,genre,height,publisher);

                books_list.add(obj);

            }

            rs.close();

            stmt.close();

            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }




    }


}
