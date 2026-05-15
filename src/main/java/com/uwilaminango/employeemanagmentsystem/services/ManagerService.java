package com.uwilaminango.employeemanagmentsystem.services;

import com.uwilaminango.employeemanagmentsystem.models.Employee;
import com.uwilaminango.employeemanagmentsystem.models.Manager;

import java.util.List;
import java.util.Optional;

public interface ManagerService {

     Manager checkManagerLogin(String username, String password);
     Optional<Manager> findManagerById(Long id);
     Manager findManagerByUsername(String username);
     Manager findManagerByEmail(String email);
     List<Manager> viewAllManagers();
     List<Employee> viewAllEmployees();
     String updateEmployeeAccountStatus(Long employeeId, String status);

     String generateResetToken(String email);
     boolean validateResetToken(String token);
     boolean changePassword(Manager manager, String oldPassword, String newPassword);
     void updatePassword(String token, String newPassword);
     void deleteResetToken (String token);
     boolean isTokenExpired(String token);
}
