package com.uwilaminango.employeemanagmentsystem.controller;

import com.uwilaminango.employeemanagmentsystem.dto.LoginRequest;
import com.uwilaminango.employeemanagmentsystem.models.Admin;
import com.uwilaminango.employeemanagmentsystem.models.Employee;
import com.uwilaminango.employeemanagmentsystem.models.Manager;
import com.uwilaminango.employeemanagmentsystem.secutity.JWTUtilizer;
import com.uwilaminango.employeemanagmentsystem.services.interfaces.AdminService;
import com.uwilaminango.employeemanagmentsystem.services.interfaces.EmployeeService;
import com.uwilaminango.employeemanagmentsystem.services.interfaces.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth/checkapi")
public class AuthController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JWTUtilizer jwtService;


    @GetMapping("/")
    public String home(){
        return "Employee Management System Backend Project is Running";
    }

    @PostMapping("/checkLogin")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){

        String identifier = loginRequest.getIdentifier();
        String password = loginRequest.getPassword();

        Admin admin = adminService.checkAdminLogin(identifier,password);
        Manager manager = managerService.checkManagerLogin(identifier, password);
        Employee employee = employeeService.checkEmployeeLogin(identifier, password);

        if(admin != null){
            String token = jwtService.generateJWTToken(admin.getUsername(), "ADMIN");
            Map<String, Object> res = new HashMap<>();
            res.put("username", "admin");
            res.put("message", "Login Successful");
            res.put("token", token);
            res.put("data", admin);

            return ResponseEntity.ok(res);
        }

        if(manager != null){
            String token = jwtService.generateJWTToken(manager.getUsername(), "MANAGER");
            Map<String, Object> res = new HashMap<>();
            res.put("username", "manager");
            res.put("message", "Login Successful");
            res.put("token", token);
            res.put("data", manager);

            return ResponseEntity.ok(res);
        }

        if(employee != null){
            if(employee.getAccountStatus().equalsIgnoreCase("Accepted")){
                String token = jwtService.generateJWTToken(employee.getUsername(), "EMPLOYEE");
                Map<String, Object> res = new HashMap<>();
                res.put("username", "employee");
                res.put("message", "Login Successful");
                res.put("token", token);
                res.put("data", employee);

                return ResponseEntity.ok(res);
            }
            else{
                return ResponseEntity.status(401).body(Map.of("message", "Account Not approved yet Please Contact Administrator"+employee.getAccountStatus()));
            }
        }
        return ResponseEntity.status(401).body(Map.of("message", "Invalid Username/Email or Password"));
    }
}
