package model;

import java.sql.Date;



public class Job {

    private int jobId;              
    private int customerId;        
    private int plumberId;          
    private int serviceId;          

    private Date scheduleDate;     
    private String status;        
    private double finalCost;       

    
    
    public Job() {}

    
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
