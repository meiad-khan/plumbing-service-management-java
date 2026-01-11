//package db;
//
//import model.Service;
//import java.sql.*;
//import java.util.ArrayList;
//
///**
// * ServiceDAO
// * ----------
// * Handles all database operations related to services.
// */
//public class ServiceDAO {
//
//    /**
//     * addService()
//     * -------------
//     * Adds a new service (Installation / Repair) to the database.
//     */
//    public boolean addService(Service service) {
//
//        String sql = "INSERT INTO services(service_type, description, base_cost) VALUES (?, ?, ?)";
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, service.getServiceType());
//            stmt.setString(2, service.getDescription());
//            stmt.setDouble(3, service.getBaseCost());
//
//            int rows = stmt.executeUpdate();
//            return rows > 0;
//
//        } catch (SQLException e) {
//            System.out.println("Error adding service: " + e.getMessage());
//            return false;
//        }
//    }
//
//    /**
//     * getAllServices()
//     * -----------------
//     * Returns a list of all available services.
//     */
//    public ArrayList<Service> getAllServices() {
//
//        ArrayList<Service> list = new ArrayList<>();
//
//        String sql = "SELECT * FROM services";
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//
//                Service service = new Service();
//
//                service.setServiceId(rs.getInt("service_id"));
//                service.setServiceType(rs.getString("service_type"));
//                service.setDescription(rs.getString("description"));
//                service.setBaseCost(rs.getDouble("base_cost"));
//
//                list.add(service);
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error fetching services: " + e.getMessage());
//        }
//
//        return list;
//    }
//
//    /**
//     * getServiceById()
//     * -----------------
//     * Returns a single service by ID.
//     */
//    public Service getServiceById(int serviceId) {
//
//        String sql = "SELECT * FROM services WHERE service_id = ?";
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setInt(1, serviceId);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                Service service = new Service();
//
//                service.setServiceId(rs.getInt("service_id"));
//                service.setServiceType(rs.getString("service_type"));
//                service.setDescription(rs.getString("description"));
//                service.setBaseCost(rs.getDouble("base_cost"));
//
//                return service;
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error fetching service: " + e.getMessage());
//        }
//
//        return null; // If not found
//    }
//}

package db;

import model.Service;
import java.sql.*;
import java.util.ArrayList;

public class ServiceDAO {

    public boolean addService(Service service) {

        String sql = "INSERT INTO services(service_type, description, base_cost) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, service.getServiceType());
            stmt.setString(2, service.getDescription());
            stmt.setDouble(3, service.getBaseCost());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding service: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Service> getAllServices() {

        ArrayList<Service> list = new ArrayList<>();

        String sql = "SELECT * FROM services";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Service service = new Service();

                service.setServiceId(rs.getInt("service_id"));
                service.setServiceType(rs.getString("service_type"));
                service.setDescription(rs.getString("description"));
                service.setBaseCost(rs.getDouble("base_cost"));

                list.add(service);
            }

        } catch (SQLException e) {
            System.out.println("Error loading services: " + e.getMessage());
        }

        return list;
    }
}
