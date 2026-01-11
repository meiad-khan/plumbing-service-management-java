package db;

import model.Plumber;
import java.sql.*;
import java.util.ArrayList;

public class PlumberDAO {

    // ADD plumber
    public boolean addPlumber(Plumber plumber) {
        String sql = "INSERT INTO plumbers(name, phone, experience, specialization, availability, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, plumber.getName());
            stmt.setString(2, plumber.getPhone());
            stmt.setInt(3, plumber.getExperience());
            stmt.setString(4, plumber.getSpecialization());
            stmt.setString(5, plumber.getAvailability());
            stmt.setString(6, plumber.getPassword()); // NEW

            int rows = stmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error adding plumber: " + e.getMessage());
            return false;
        }
    }

    // LOGIN plumber
    public Plumber loginPlumber(String phone, String password) {
        String sql = "SELECT * FROM plumbers WHERE phone = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, phone);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Plumber plumber = new Plumber();

                plumber.setPlumberId(rs.getInt("plumber_id"));
                plumber.setName(rs.getString("name"));
                plumber.setPhone(rs.getString("phone"));
                plumber.setExperience(rs.getInt("experience"));
                plumber.setSpecialization(rs.getString("specialization"));
                plumber.setAvailability(rs.getString("availability"));
                plumber.setPassword(rs.getString("password"));

                return plumber;
            }

        } catch (SQLException e) {
            System.out.println("Login plumber error: " + e.getMessage());
        }

        return null;
    }

    // UPDATE availability
    public boolean updateAvailability(int plumberId, String availability) {
        String sql = "UPDATE plumbers SET availability = ? WHERE plumber_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, availability);
            stmt.setInt(2, plumberId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Availability update failed: " + e.getMessage());
            return false;
        }
    }

    // FULL list of plumbers
    public ArrayList<Plumber> getAllPlumbers() {

        ArrayList<Plumber> list = new ArrayList<>();
        String sql = "SELECT * FROM plumbers";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Plumber plumber = new Plumber();
                plumber.setPlumberId(rs.getInt("plumber_id"));
                plumber.setName(rs.getString("name"));
                plumber.setPhone(rs.getString("phone"));
                plumber.setExperience(rs.getInt("experience"));
                plumber.setSpecialization(rs.getString("specialization"));
                plumber.setAvailability(rs.getString("availability"));
                plumber.setPassword(rs.getString("password"));

                list.add(plumber);
            }

        } catch (SQLException e) {
            System.out.println("Error getting plumbers: " + e.getMessage());
        }

        return list;
    }
    public ArrayList<Plumber> getAvailablePlumbers() {
    ArrayList<Plumber> list = new ArrayList<>();

    String sql = "SELECT * FROM plumbers WHERE availability = 'Available'";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Plumber plumber = new Plumber();

            plumber.setPlumberId(rs.getInt("plumber_id"));
            plumber.setName(rs.getString("name"));
            plumber.setPhone(rs.getString("phone"));
            plumber.setExperience(rs.getInt("experience"));
            plumber.setSpecialization(rs.getString("specialization"));
            plumber.setAvailability(rs.getString("availability"));
            plumber.setPassword(rs.getString("password"));

            list.add(plumber);
        }

    } catch (SQLException e) {
        System.out.println("Error fetching available plumbers: " + e.getMessage());
    }

    return list;
}

}
