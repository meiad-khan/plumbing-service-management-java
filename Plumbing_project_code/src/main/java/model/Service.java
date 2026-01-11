//package model;
//
///**
// * Service Model Class
// * ---------------------
// * This class represents a Service in the system.
// * It matches the "services" table in the database.
// *
// * Example:
// *  - Installation
// *  - Repair
// */
//public class Service {
//
//    private int serviceId;          // Primary key
//    private String serviceType;     // Installation or Repair
//    private String description;     // Short description
//    private double baseCost;        // Base price
//
//    /**
//     * Default constructor
//     * Needed for reading data from the database.
//     */
//    public Service() {}
//
//    /**
//     * Parameterized constructor
//     * Used when admin adds a new service.
//     */
//    public Service(String serviceType, String description, double baseCost) {
//        this.serviceType = serviceType;
//        this.description = description;
//        this.baseCost = baseCost;
//    }
//
//    // --------- Getters and Setters ----------
//
//    public int getServiceId() {
//        return serviceId;
//    }
//
//    public void setServiceId(int serviceId) {
//        this.serviceId = serviceId;
//    }
//
//    public String getServiceType() {
//        return serviceType;
//    }
//
//    public void setServiceType(String serviceType) {
//        this.serviceType = serviceType;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public double getBaseCost() {
//        return baseCost;
//    }
//
//    public void setBaseCost(double baseCost) {
//        this.baseCost = baseCost;
//    }
//}
package model;

public class Service {

    private int serviceId;
    private String serviceType;
    private String description;
    private double baseCost;

    public Service() {}

    public Service(String serviceType, String description, double baseCost) {
        this.serviceType = serviceType;
        this.description = description;
        this.baseCost = baseCost;
    }

    // Getters & Setters
    public int getServiceId() { return serviceId; }
    public void setServiceId(int serviceId) { this.serviceId = serviceId; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getBaseCost() { return baseCost; }
    public void setBaseCost(double baseCost) { this.baseCost = baseCost; }
}
