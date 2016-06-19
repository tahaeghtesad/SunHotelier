package ir.arcinc.model;

import java.util.UUID;

/**
 * Created by tahae on 6/19/2016.
 */
public class User {
    private UUID userId;
    private String name;
    private String phone;
    private String username;
    private String  password;
    private String email;

    public User(UUID userId, String name, String phone, String username, String password, String email) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String name, String phone, String username, String password, String email){
        this(null, name,phone,username,password,email);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password=" + password +
                ", email='" + email + '\'' +
                '}';
    }
}
