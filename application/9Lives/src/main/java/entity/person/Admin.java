package entity.person;

public class Admin {
    private final int adminId;
    private String name;

    public Admin(int adminId, String name) {
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

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", name='" + name + '\'' +
                '}';
    }
}
