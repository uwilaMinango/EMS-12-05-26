package com.uwilaminango.employeemanagmentsystem.services;

import com.uwilaminango.employeemanagmentsystem.models.Duty;
import com.uwilaminango.employeemanagmentsystem.models.Employee;
import com.uwilaminango.employeemanagmentsystem.models.ResetToken;
import com.uwilaminango.employeemanagmentsystem.repository.DutyRepository;
import com.uwilaminango.employeemanagmentsystem.repository.EmployeeRepository;
import com.uwilaminango.employeemanagmentsystem.repository.ResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DutyRepository dutyRepository;
    @Autowired
    private ResetTokenRepository resetTokenRepository;

    @Override
    public Employee checkEmployeeLogin(String username, String password) {
        return employeeRepository.findByUsernameAndPassword(username,password);
    }

    @Override
    public String registerEmployee(Employee emp) {
        Long eId = generateRandomEmployeeId();
        emp.setId(eId);

        String randomPassword = generateRandomPassword(8);
        emp.setPassword(randomPassword);
        emp.setAccountStatus("Pending");
        emp.setRole("Employee");

        employeeRepository.save(emp);
        return "Employee Registered Successfully";
    }

    @Override
    public String updateEmployeeProfile(Employee emp) {
      employeeRepository.save(emp);
      return "Employee updated successfully";
    }

    @Override
    public Employee findEmployeeById(Long id) {
       return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee findEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public List<Employee> viewAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public String updateAccountStatus(Long empId, String status) {
        Optional<Employee> emp = employeeRepository.findById(empId);

        if(emp.isPresent()){
            Employee e = new Employee();
            e.setAccountStatus(status);
            employeeRepository.save(e);
            return "Status Updated to:"+ status;
        }
        return "Employee ID Not Found";
    }

    @Override
    public List<Duty> viewAllAssignedDuties(Long empId) {
        Employee emp = employeeRepository.findById(empId).orElse(null);
        if(emp != null){
            return dutyRepository.findByEmployee(emp);
        }
        return Collections.emptyList();
    }

    @Override
    public String generateResetToken(String email) {
        Optional<Employee> employee = employeeRepository.FindByEmail(email);
        if(employee.isPresent()){
            String token = UUID.randomUUID().toString();//Automatically generates a random id
            ResetToken rt = new ResetToken();
            rt.setToken(token);
            rt.setEmail(email);
            rt.setCreatedAt(LocalDateTime.now());
            rt.setExpiresAt(LocalDateTime.now().plusMinutes(5));//token will only be handled for 5 minutes
            resetTokenRepository.save(rt);
            return token;
        }
        return null;
    }

    @Override
    public boolean validateResetToken(String token) {
        Optional<ResetToken> rt = resetTokenRepository.findByToken(token);
        return rt.isPresent() && !isTokenExpired(token);
    }

    @Override
    public boolean changePassword(Employee employee, String oldPassword, String newPassword) {
        if(employee.getPassword().equals(oldPassword)){
            employee.setPassword(newPassword);

            employeeRepository.save(employee);
            return true;
        }
        return false;
    }

    @Override
    public void updatePassword(String token, String newPassword) {
        Optional<ResetToken> resetToken = resetTokenRepository.findByToken(token);
        if(resetToken.isPresent() && !isTokenExpired(token)){
            Employee e = new Employee();
            e.setPassword(newPassword);
            employeeRepository.save(e);
            deleteResetToken(token);
        }
    }

    @Override
    public void deleteResetToken(String token) {
        resetTokenRepository.deleteByToken(token);
    }

    @Override
    public boolean isTokenExpired(String token) {
        Optional<ResetToken> rt = resetTokenRepository.findByToken(token);
        if(rt.isPresent()){
            return rt.get().getExpiresAt().isBefore(LocalDateTime.now());
        }
        return true;
    }

    private Long generateRandomEmployeeId(){
            Random random = new Random();
            return 1000 + random.nextLong(9000);
        }

        private String generateRandomPassword(int length){
            String upper = "ABCDEFGHIJKLNMOPQRSTUVWXYZ";
            String lower = "abcdefghijklmnopqrstuvwxyz";
            String digits = "0123456789";
            String special="!@#$%^&*";
            String combined = upper + lower + digits + special;

            StringBuilder sb = new StringBuilder();
            Random random = new Random();

            sb.append(upper.charAt(random.nextInt(upper.length())));

            sb.append(lower.charAt(random.nextInt(lower.length())));

            sb.append(digits.charAt(random.nextInt(digits.length())));

            sb.append(special.charAt(random.nextInt(special.length())));

            for(int i = 4; i<length; i++){
                sb.append(combined.charAt(random.nextInt(combined.length())));
            }

            return sb.toString();
        }
}



