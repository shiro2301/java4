package com.poly.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Users")
public class User implements Serializable {
    @Id
    @Column(name = "Username")
    private String username;
    
    @Column(name = "Password")
    private String password;
    
    @Column(name = "HoVaTen")
    private String hoVaTen;

    public User() {}
    // Constructor, Getters, Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getHoVaTen() { return hoVaTen; }
    public void setHoVaTen(String hoVaTen) { this.hoVaTen = hoVaTen; }

    @Override
    public String toString() {
        return hoVaTen; // Hiển thị họ và tên (theo yêu cầu Bài 1)
    }
}
