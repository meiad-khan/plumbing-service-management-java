package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection class
 * -------------------
 * This class is responsible for connecting our Java program to the MySQL database.
 * We will use this connection in every DAO class (CustomerDAO, PlumberDAO, etc).
 */
public class DBConnection {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/plumbing_service_db";
    private static final String USER = "root";   // your mysql username
    private static final String PASSWORD = "";   // your mysql password (keep empty if none)

    /**
     * getConnection()
     * ----------------
     * This method connects to MySQL using the credentials above.
     * It returns a Connection object that DAO classes will use.
     */
    public static Connection getConnection() {
        Connection conn = null;

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create actual connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }

        return conn;
    }
    public static void main(String[] args) {
        getConnection();
    }
}