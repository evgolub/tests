package com.haron;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtils {

    public static Connection getMyConnection() throws SQLException,
            ClassNotFoundException {


        return OracleConnUtils.getOracleConnection();
    }

        public static void main(String[] args)  throws ClassNotFoundException,
                SQLException {

            Connection connection = ConnectionUtils.getMyConnection();

            Statement statement = connection.createStatement();

            String sql = "SELECT * from table(test_pkg_3.GET_NAME)";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String City_id = rs.getString(3);
                System.out.println("----------------------------------------");
                System.out.println("id:" + id + " name:" + name + " city_id:" + City_id);
            }
            rs.close();
            // Close connection.
            connection.close();
        }

    }

