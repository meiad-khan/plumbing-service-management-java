package model;

import java.sql.Date;

/**
 * Job Model Class
 * ----------------
 * Represents a plumbing job booked by a customer.
 * Matches the "jobs" table in the database.
 */
public class Job {

    private int jobId;              // Primary key
    private int customerId;         // Linked to customers table
    private int plumberId;          // Linked to plumbers table (nullable)
    private int serviceId;          // Linked to services table

    private Date scheduleDate;      // Job schedule date
    private String status;          // Pending / In Progress / Completed
    private double finalCost;       // Calculated cost

    /**
     * Default constructor
     * Needed when reading data from database.
     */
    public Job() {}

    /**
     * Constructor used when customer books a service.
     * Plumber ID is null at this point (waits for admin assignment).
     */
    public Job(int customerId, int serviceId, Date scheduleDate, String status) {
        this.customerId = customerId;
        this.serviceId = serviceId;
        this.scheduleDate = scheduleDate;
        this.status = status;
    }

    // ---------------- Getters & Setters ----------------

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPlumberId() {
        return plumberId;
    }

    public void setPlumberId(int plumberId) {
        this.plumberId = plumberId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }
}
