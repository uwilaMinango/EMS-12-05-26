package com.uwilaminango.employeemanagmentsystem.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="admin_table")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable= false, unique= true)
    private String username;

    @Column(nullable= false)
    private String password;

    @Column(nullable= false, unique= true)
    private String email;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
