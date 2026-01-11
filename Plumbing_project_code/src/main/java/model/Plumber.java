package model;

public class Plumber {

    private int plumberId;
    private String name;
    private String phone;
    private int experience;
    private String specialization;
    private String availability;
    private String password; // NEW

    public Plumber() {}

    public Plumber(String name, String phone, int experience, String specialization, String availability, String password) {
        this.name = name;
        this.phone = phone;
        this.experience = experience;
        this.specialization = specialization;
        this.availability = availability;
        this.password = password;
    }

    public int getPlumberId() { return plumberId; }
    public void setPlumberId(int plumberId) { this.plumberId = plumberId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
