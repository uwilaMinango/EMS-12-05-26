package com.uwilaminango.employeemanagmentsystem.services.classes;

import com.uwilaminango.employeemanagmentsystem.models.*;
import com.uwilaminango.employeemanagmentsystem.repository.*;
import com.uwilaminango.employeemanagmentsystem.services.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LeaveRepository leaveRepository;

    @Override
    public Admin checkAdminLogin(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Manager addManager(Manager manager) {
        Long manager_id = generateRandomManagerId();
        String randomPassword = generateRandomPassword(8);

        manager.setId(manager_id);
        manager.setPassword(randomPassword);

        Manager savedManager = managerRepository.save(manager);

        Email e = new Email();
        e.setRecipient(manager.getEmail());

        e.setSubject("Welcome Manager to EMS");

        e.setMessage("Hola"+ manager.getName()+
                ".\n\nYou have been Successfully added.\n\nManager ID:"
                +manager.getId()+"Here is your username: "+ manager.getUsername()+
                "\nPassword: "+ manager.getPassword());

        emailRepository.save(e);
        emailService.sendEmail(e.getRecipient(), e.getSubject(), e.getMessage());

        return savedManager;
    }

    @Override
    public List<Manager> viewAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public String deleteManager(Long mid) {
        Optional<Manager> manager = managerRepository.findById(mid);

        if(manager.isPresent()){
            managerRepository.deleteById(mid);
            return "Manager Deleted Successfully...!!!";
        }
        else{
            return "Manager ID not Found";
        }
    }

    @Override
    public List<Employee> viewAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public String deleteEmployee(Long eid) {
        Optional<Employee> employee = employeeRepository.findById(eid);

        if(employee.isPresent()){
            employeeRepository.deleteById(eid);
            return "Employee Deleted Successfully...!!!";
        }
        else{
            return "Employee ID not Found";
        }
    }

    @Override
    public Long managerCount() {
        return managerRepository.count();
    }

    @Override
    public Long employeeCount() {
        return employeeRepository.count();
    }

//    @Override
//    public String assignDutyToManager(Duty duty, Long managerId) {
//        Optional <Manager> manager = managerRepository.findById(managerId);
//
//        if(manager.isPresent()){
//            duty.
//        }
//    }
//
//    @Override
//    public String assignDutyToEmployee(Duty duty, Long employeeId) {
//        return "";
//    }

    @Override
    public List<Leave> viewAllLeaveApplications() {
        return leaveRepository.findAll();
    }

    private Long generateRandomManagerId(){
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
