package com.uwilaminango.employeemanagmentsystem.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="manager_table")
public class Manager {

    @Id
    @Column(name="manager_id")
    private Long id;
    @Column(name="manager_name", nullable=false)
    private String name;
    @Column(name="manager_email", nullable=false, unique=true)
    private String email;
    @Column(name="manager_password", nullable=false)
    private String password;
    @Column(name="manager_dept", nullable=false)
    private String department;
    @Column(name="manager_contact", nullable=false, unique=true)
    private String contact;
    @Column(name="manager_username", nullable=false, unique=true)
    private String username;

    @OneToMany(mappedBy = "manager", cascade= CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy="assignedByManager", cascade=CascadeType.ALL)
    private List<Duty> dutiesAssigned;

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", contact='" + contact + '\'' +
                ", username='" + username + '\'' +
                ", employees=" + employees +
                ", dutiesAssigned=" + dutiesAssigned +
                '}';
    }
}
