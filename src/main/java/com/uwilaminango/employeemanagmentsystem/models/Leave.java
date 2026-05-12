package com.uwilaminango.employeemanagmentsystem.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="leave_table")
public class Leave {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(nullable= false)
    private LocalDate startDate;
    @Column(nullable= false)
    private LocalDate endDate;
    @Column(nullable= false)
    private String reason;
    @Column(nullable= false)
    private String status;

    @ManyToOne
    @JoinColumn(name="emp_id")
    private Employee employee;
}
