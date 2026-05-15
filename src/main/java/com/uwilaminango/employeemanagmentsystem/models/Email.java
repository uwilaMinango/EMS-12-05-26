package com.uwilaminango.employeemanagmentsystem.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="email_details")
public class Email {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false)
    private String recipient;

    @Column(nullable=false)
    private String subject;

    @Column(nullable=false, length=1000)
    private String message;

    @Column(nullable=false)
    private LocalDateTime sentAt;

    @Column(nullable=false)
    private String status;

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", sentAt=" + sentAt +
                ", status='" + status + '\'' +
                '}';
    }
}
