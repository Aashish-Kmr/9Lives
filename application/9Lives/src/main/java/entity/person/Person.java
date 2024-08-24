package entity.person;

public abstract class Person {
    private final int personId;
    private final String role;
    private String username;
    private String password;
    private String email;

    public Person(int id, String username, String password, String email, String role) {
        this.personId = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public int getPersonId() {
        return personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }
}
