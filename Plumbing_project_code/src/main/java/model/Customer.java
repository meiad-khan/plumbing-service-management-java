package model;

/**
 * Customer Model Class
 * ---------------------
 * This class represents a Customer object in our system.
 * It matches the "customers" table in the database.
 */
public class Customer {

    private int customerId;   // Primary key from DB
    private String name;
    private String phone;
    private String address;
    private String password;

    /**
     * Default constructor
     * -------------------
     * Needed so Java can create an empty Customer object.
     * Also required for some frameworks & reading from DB.
     */
    public Customer() {}

    /**
     * Parameterized constructor
     * -------------------------
     * Useful when creating a new customer (during registration).
     */
    public Customer(String name, String phone, String address, String password) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    // Getter and Setter methods for each private variable

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
