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
                String name = rs.getString(1);
                String c_name = rs.getString(2);
                String f_name = rs.getString(3);
                String v_name = rs.getString(4);
                System.out.println("-------------------------");
                System.out.println("Имя: " + name + "\nГород: " + c_name + "\nФрукт: " + f_name + "\nОвощ: " + v_name);
            }
            rs.close();
            // Close connection.
            connection.close();
        }

    }

