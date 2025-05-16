package com.mcdade.payapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is responsible for creating a connection to the SQL Server database.
 * It uses JDBC (Java Database Connectivity) to talk to the database.
 */
public class DatabaseConnection {
    
    // JDBC URL that tells Java how to connect to your local SQL Server
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=PayAppDB;";
    
    // Connection settings
    private static final String INTEGRATED_SECURITY = "true"; // Use Windows login credentials
    private static final String ENCRYPT = "true";              // Use encryption
    private static final String TRUST_CERT = "true";           // Trust the server's SSL certificate

    /**
     * Returns an active connection to the database.
     * @return Connection object
     * @throws SQLException if something goes wrong when connecting
     * @throws ClassNotFoundException if the SQL Server JDBC driver isn't found
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Load the JDBC driver class for SQL Server
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // Build and return the connection using the driver manager
        return DriverManager.getConnection(
            URL + "integratedSecurity=" + INTEGRATED_SECURITY + ";" +
            "encrypt=" + ENCRYPT + ";" +
            "trustServerCertificate=" + TRUST_CERT + ";"
        );
    }
}
