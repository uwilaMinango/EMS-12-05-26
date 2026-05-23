package com.uwilaminango.employeemanagmentsystem.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String identifier;//email or username
    private String password;

    @Override
    public String toString() {
        return "LoginRequest{" +
                "identifier='" + identifier + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
