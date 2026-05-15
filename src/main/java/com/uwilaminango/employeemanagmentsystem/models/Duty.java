package com.uwilaminango.employeemanagmentsystem.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="duty_table")
public class Duty {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String title;
    @Column(nullable=false, length=3000)
    private String description;


    @ManyToOne
    @JoinColumn(name="emp_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="assignedByManager")
    private Manager assignedByManager;

    @ManyToOne
    @JoinColumn(name="assignedByAdmin")
    private Admin assignedByAdmin;

    @Override
    public String toString() {
        return "Duty{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", employee=" + employee +
                ", assignedByManager=" + assignedByManager +
                ", assignedByAdmin=" + assignedByAdmin +
                '}';
    }
}
