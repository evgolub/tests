package com.haron;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.haron.*;

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

           // String sql = "Select Emp_Id, Emp_No, Emp_Name from Employee";
            //// String sql = "{Get_First_Name}";
            // Execute SQL statement returns a ResultSet object.
            ResultSet rs = statement.executeQuery(sql);

            // Fetch on the ResultSet
            // Move the cursor to the next record.
            while (rs.next()) {
                int empId = rs.getInt(1);
                String empNo = rs.getString(2);
                int empName = rs.getInt(3);
                System.out.println("--------------------");

                System.out.println("EmpId:" + empId);
                System.out.println("EmpNo:" + empNo);
                System.out.println("EmpName:" + empName );

            }

            // Close connection.
            connection.close();
        }

    }

