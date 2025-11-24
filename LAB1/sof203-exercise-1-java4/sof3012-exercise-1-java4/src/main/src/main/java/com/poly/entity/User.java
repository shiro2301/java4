package com.poly.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Fullname")
    private String fullname;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Admin")
    private boolean admin;

    // ⚙Constructor rỗng (bắt buộc cho Hibernate)
    public User() {}

    // ⚙Constructor tiện dụng
    public User(String fullname, String email, String password, boolean admin) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    // ⚙️ Getters & Setters (Hibernate cần dùng)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAdmin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }
}
