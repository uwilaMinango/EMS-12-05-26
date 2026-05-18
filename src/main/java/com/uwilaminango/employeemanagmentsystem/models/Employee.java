package com.uwilaminango.employeemanagmentsystem.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "employee_table")
public class Employee {

    @Id
    @Column(name="emp_id")
    private Long id;
    @Column(name="emp_name", nullable=false)
    private String name;

    @Column(name="emp_gender", nullable=false)
    private String gender;

    @Column(name="emp_age", nullable=false)
    private int age;

    @Column(name="emp_designation", nullable=false)
    private String designation;

    @Column(name="emp_dept", nullable=false)
    private String department;

    @Column(name="emp_salary", nullable=false)
    private double salary;

    @Column(name="emp_email", nullable=false)
    private String email;

    @Column(name="emp_password", nullable=false)
    private String password;

    @Column(name="emp_username", nullable=false, unique= true)
    private String username;

    @Column(name="emp_contact", nullable=false, unique= true)
    private String contact;

    @Column(name="acc_status", nullable=false)
    private String accountStatus;

    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Leave> leave;

    @OneToMany(mappedBy = "employee", cascade= CascadeType.ALL)
    private List<Duty> duty;

    @ManyToOne
    @JoinColumn(name="manager_id")
    private Manager manager;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", designation='" + designation + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", contact='" + contact + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", leave=" + leave +
                ", duty=" + duty +
                ", manager=" + manager +
                '}';
    }
}
