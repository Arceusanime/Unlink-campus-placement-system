package com.unlink.test;

import com.unlink.config.DBConnection;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args){
        try {
            Connection connection =
                    DBConnection.getConnection();

            if(connection != null){
                System.out.println("Database connection Successful");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
