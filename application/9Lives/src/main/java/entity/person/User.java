package entity.person;

public class User extends Person {
    private final int userId;
    private int phoneNo;

    public User(int personId, String username, String password, String email, String role, int userId, int phoneNo) {
        super(personId, username, password, email, role);
        this.userId = userId;
        this.phoneNo = phoneNo;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getUserId() {
        return userId;
    }
}
