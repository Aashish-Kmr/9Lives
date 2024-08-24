package entity.person;

public class Admin extends Person {
    private final int adminId;
    private String name;

    public Admin(int personId, String username, String password, String email, String role, int adminId, String name) {
        super(personId, username, password, email, role);
        this.adminId = adminId;
        this.name = name;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
