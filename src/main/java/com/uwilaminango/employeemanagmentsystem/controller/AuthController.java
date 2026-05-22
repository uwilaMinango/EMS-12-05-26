package com.uwilaminango.employeemanagmentsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/checkapi")
public class AuthController {

    @GetMapping("/")
    public String home(){
        return "Employee Management System Backend Project is Running";
    }

    @PostMapping("/checkLogin")
    public ResponseEntity<?> login(){

    }
}
