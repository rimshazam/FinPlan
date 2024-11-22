package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersistenceHandler {

    private static final String URL = "jdbc:mysql://localhost:3306/finplandatabase"; // Replace with your DB URL
    private static final String USER = "root"; // Replace with your DB username
    private static final String PASSWORD = "rymes4"; // Replace with your DB password

    private static Connection connection;

    // Establish a single connection to the database
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully!");
            } catch (SQLException e) {
                System.err.println("Failed to connect to the database: " + e.getMessage());
            }
        }
        return connection;
    }

    // Method to execute SELECT queries
    public ResultSet executeQuery(String query, Object... params) throws SQLException {
        PreparedStatement statement = prepareStatement(query, params);
        return statement.executeQuery();
    }

    // Method to execute INSERT, UPDATE, DELETE queries
    public int executeUpdate(String query, Object... params) throws SQLException {
        PreparedStatement statement = prepareStatement(query, params);
        return statement.executeUpdate();
    }

    // Helper method to prepare a statement with parameters
    private PreparedStatement prepareStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        return statement;
    }
    
    
    // Method to save a transaction
    public boolean saveTransaction(String category, String transactionDate, String account, double amount, String transactionType) {
        String query = "INSERT INTO Transactions (category, transaction_date, account, amount, transaction_type) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, category);
            statement.setString(2, transactionDate);
            statement.setString(3, account);
            statement.setDouble(4, amount);
            statement.setString(5, transactionType);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected); // Debug: Check how many rows were affected
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error while saving transaction: " + e.getMessage());
            return false;
        }
    }

    // Close the connection (optional for cleanup)
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close the connection: " + e.getMessage());
            }
        }
    }
    
    
}
