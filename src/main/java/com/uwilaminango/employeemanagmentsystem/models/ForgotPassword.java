package com.uwilaminango.employeemanagmentsystem.models;

import lombok.Data;

@Data
public class ForgotPassword {

    private String email;
    private String newPassword;

    @Override
    public String toString() {
        return "ForgotPassword{" +
                "email='" + email + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
