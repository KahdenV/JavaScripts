package com.mcdade.payapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for interacting with the VendorT table in the database.
 * "DAO" stands for Data Access Object – it handles database operations for one type of data (Vendor).
 */
public class VendorDAO {

    /**
     * This method queries all vendor names from the VendorT table.
     * @return List of vendor names as strings
     */
    public List<String> getAllVendors() {
        List<String> vendors = new ArrayList<>(); // Holds the results from the database

        try (
            // Get a connection to the database (calls our DatabaseConnection class)
            Connection conn = DatabaseConnection.getConnection();

            // Create an object to send SQL commands to the database
            Statement stmt = conn.createStatement();

            // Run a SQL query to get vendor names from VendorT
            ResultSet rs = stmt.executeQuery("SELECT VendorName FROM VendorT");
        ) {
            // Loop through each row of the result set
            while (rs.next()) {
                // Get the vendor name in the current row and add it to the list
                vendors.add(rs.getString("VendorName"));
            }

        } catch (Exception e) {
            // If something goes wrong, print the error
            e.printStackTrace();
        }

        return vendors; // Return the list of vendor names
    }
}