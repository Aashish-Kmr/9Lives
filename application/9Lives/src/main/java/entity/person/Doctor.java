package entity.person;

public class Doctor extends Person {
    private final int doctorId;
    private String firstName;
    private String lastName;
    private String specialization;

    public Doctor(String username, String password, String email, String role, int doctorId, String firstName, String lastName, String specialization) {
        super(username, password, email, role);
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
