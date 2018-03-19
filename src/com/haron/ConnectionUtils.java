package com.haron;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

////import com.haron.*;

public class ConnectionUtils {

    public static Connection getMyConnection() throws SQLException,
            ClassNotFoundException {

        // Using Oracle
        // You may be replaced by other Database.
        return OracleConnUtils.getOracleConnection();
    }



        public static void main(String[] args)  throws ClassNotFoundException,
                SQLException {

            //+ Get Connection
            Connection connection = ConnectionUtils.getMyConnection();

            // Create statement
            Statement statement = connection.createStatement();

            String sql = "SELECT id, name, city_id FROM people";

            // Execute SQL statement returns a ResultSet object.
            ResultSet rs = statement.executeQuery(sql);

            // Fetch on the ResultSet
            // Move the cursor to the next record.
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int city_id = rs.getInt(3);
                System.out.println("--------------------");

                System.out.println("people_id:" + id);
                System.out.println("name:" + name);
                System.out.println("city_id:" + city_id );

            }
            rs.close();
            // Close connection.
            connection.close();
        }

    }

