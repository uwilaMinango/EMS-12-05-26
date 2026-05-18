package com.uwilaminango.employeemanagmentsystem.services.interfaces;

import com.uwilaminango.employeemanagmentsystem.models.Duty;
import com.uwilaminango.employeemanagmentsystem.models.Employee;


import java.util.List;

public interface EmployeeService {
    Employee checkEmployeeLogin(String username, String password);
    String registerEmployee(Employee emp);
    String updateEmployeeProfile(Employee emp);
    Employee findEmployeeById(Long id);
    Employee findEmployeeByUsername(String username);
    Employee findEmployeeByEmail(String email);
    List<Employee> viewAllEmployees();

    String updateAccountStatus(Long empId, String Status);
    List<Duty> viewAllAssignedDuties(Long empId);

    String generateResetToken(String email);
    boolean validateResetToken(String token);
    boolean changePassword(Employee employee, String oldPassword, String newPassword);
    void updatePassword(String token, String newPassword);
    void deleteResetToken (String token);
    boolean isTokenExpired(String token);
}
