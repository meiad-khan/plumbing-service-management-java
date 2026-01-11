package db;

import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerDAO {

    
    
    public boolean registerCustomer(Customer customer) {
        String sql = "INSERT INTO customers(name, phone, address, password) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

           
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPassword());

           
            int rows = stmt.executeUpdate();

            return rows > 0; 

        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }

    
    public Customer loginCustomer(String phone, String password) {

        String sql = "SELECT * FROM customers WHERE phone = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, phone);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

           
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setPassword(rs.getString("password"));

                return customer; 
            }

        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
        }

        return null; 
    }

    
    public Customer getCustomerByPhone(String phone) {

        String sql = "SELECT * FROM customers WHERE phone = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, phone);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setPassword(rs.getString("password"));

                return customer;
            }

        } catch (SQLException e) {
            System.out.println("Error fetching customer: " + e.getMessage());
        }

        return null;
    }
    

    
public ArrayList<model.Customer> getAllCustomers() {
    ArrayList<model.Customer> list = new ArrayList<>();
    String sql = "SELECT * FROM customers ORDER BY customer_id DESC";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            model.Customer c = new model.Customer();
            c.setCustomerId(rs.getInt("customer_id"));
            c.setName(rs.getString("name"));
            c.setPhone(rs.getString("phone"));
            c.setAddress(rs.getString("address"));
            c.setPassword(rs.getString("password"));
            list.add(c);
        }

    } catch (SQLException e) {
        System.out.println("Error fetching customers: " + e.getMessage());
    }

    return list;
}



}
