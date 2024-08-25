package entity.person;

public class User {
    private final int userId;
    private String phoneNo;

    public User(int userId, String phoneNo) {
        this.userId = userId;
        this.phoneNo = phoneNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", phoneNo=" + phoneNo +
                '}';
    }
}
