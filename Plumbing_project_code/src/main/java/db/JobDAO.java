package db;

import model.Job;
import java.sql.*;
import java.util.ArrayList;

/**
 * JobDAO
 * -------
 * Handles all database operations related to jobs.
 * (booking, assigning plumber, updating status, fetching jobs)
 */
public class JobDAO {

    /**
     * createJob()
     * ------------
     * Called when a customer books a service.
     * Plumber is NOT assigned at this stage.
     */
    public boolean createJob(Job job) {

        String sql = "INSERT INTO jobs(customer_id, service_id, schedule_date, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, job.getCustomerId());
            stmt.setInt(2, job.getServiceId());
            stmt.setDate(3, job.getScheduleDate());
            stmt.setString(4, job.getStatus());   // usually "Pending"

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error creating job: " + e.getMessage());
            return false;
        }
    }

    /**
     * assignPlumber()
     * ----------------
     * Admin assigns a plumber to a job.
     */
    public boolean assignPlumber(int jobId, int plumberId) {

        String sql = "UPDATE jobs SET plumber_id = ?, status = 'In Progress' WHERE job_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, plumberId);
            stmt.setInt(2, jobId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error assigning plumber: " + e.getMessage());
            return false;
        }
    }

    /**
     * updateStatus()
     * ---------------
     * Plumber updates the job status.
     * status can be: Pending / In Progress / Completed
     */
    public boolean updateStatus(int jobId, String status) {

        String sql = "UPDATE jobs SET status = ? WHERE job_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, jobId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating job status: " + e.getMessage());
            return false;
        }
    }

    /**
     * updateFinalCost()
     * -------------------
     * Saves the total calculated cost in the job record.
     */
    public boolean updateFinalCost(int jobId, double cost) {

        String sql = "UPDATE jobs SET final_cost = ? WHERE job_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, cost);
            stmt.setInt(2, jobId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating cost: " + e.getMessage());
            return false;
        }
    }

    /**
     * getJobsByCustomer()
     * --------------------
     * Shows all jobs booked by a specific customer.
     */
    public ArrayList<Job> getJobsByCustomer(int customerId) {

        ArrayList<Job> list = new ArrayList<>();

        String sql = "SELECT * FROM jobs WHERE customer_id = ? ORDER BY job_id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Job job = new Job();

                job.setJobId(rs.getInt("job_id"));
                job.setCustomerId(rs.getInt("customer_id"));
                job.setPlumberId(rs.getInt("plumber_id"));
                job.setServiceId(rs.getInt("service_id"));
                job.setScheduleDate(rs.getDate("schedule_date"));
                job.setStatus(rs.getString("status"));
                job.setFinalCost(rs.getDouble("final_cost"));

                list.add(job);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching jobs: " + e.getMessage());
        }

        return list;
    }

    /**
     * getJobsByPlumber()
     * -------------------
     * Plumber sees his assigned jobs.
     */
    public ArrayList<Job> getJobsByPlumber(int plumberId) {

        ArrayList<Job> list = new ArrayList<>();

        String sql = "SELECT * FROM jobs WHERE plumber_id = ? ORDER BY job_id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, plumberId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Job job = new Job();

                job.setJobId(rs.getInt("job_id"));
                job.setCustomerId(rs.getInt("customer_id"));
                job.setPlumberId(rs.getInt("plumber_id"));
                job.setServiceId(rs.getInt("service_id"));
                job.setScheduleDate(rs.getDate("schedule_date"));
                job.setStatus(rs.getString("status"));
                job.setFinalCost(rs.getDouble("final_cost"));

                list.add(job);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching plumber jobs: " + e.getMessage());
        }

        return list;
    }
    /**
 * getPendingJobs()
 * ----------------
 * Returns list of jobs with status = 'Pending'
 */
public ArrayList<Job> getPendingJobs() {
    ArrayList<Job> list = new ArrayList<>();
    String sql = "SELECT * FROM jobs WHERE status = 'Pending' ORDER BY job_id DESC";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Job job = new Job();
            job.setJobId(rs.getInt("job_id"));
            job.setCustomerId(rs.getInt("customer_id"));
            job.setPlumberId(rs.getInt("plumber_id"));
            job.setServiceId(rs.getInt("service_id"));
            job.setScheduleDate(rs.getDate("schedule_date"));
            job.setStatus(rs.getString("status"));
            job.setFinalCost(rs.getDouble("final_cost"));
            list.add(job);
        }

    } catch (SQLException e) {
        System.out.println("Error fetching pending jobs: " + e.getMessage());
    }

    return list;
}

}
