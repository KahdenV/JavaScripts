package com.mcdade.payapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public List<String> getAllEmployees() {
        List<String> employees = new ArrayList<>(); // Holds the results from the database

        try (
            // Get a connection to the database (calls our DatabaseConnection class)
            Connection conn = DatabaseConnection.getConnection();

            // Create an object to send SQL commands to the database
            Statement stmt = conn.createStatement();

            // Run a SQL query to get employee names from EmployeeT
            ResultSet rs = stmt.executeQuery("SELECT EmployeeID, EmployeeNumber, EmployeeFirstName, EmployeeLastName, Position, PrimaryPhone, SecondaryPhone, IsActive FROM EmployeeT");
        ) {
            // Loop through each row of the result set
            while (rs.next()) {
                // Get the employee name in the current row and add it to the list
                employees.add(rs.getString("EmployeeName"));
            }

        } catch (Exception e) {
            // If something goes wrong, print the error
            e.printStackTrace();
        }

        return employees; // Return the list of employee names
    }
}
