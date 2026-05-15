package com.uwilaminango.employeemanagmentsystem.services;

import com.uwilaminango.employeemanagmentsystem.models.*;

import java.util.List;

public interface AdminService {

    public Admin checkAdminLogin(String username, String password);

    Manager addManager(Manager manager);
    List<Manager> viewAllManagers();
    String deleteManager(Long eid);
    List <Employee> viewAllEmployees();
    String deleteEmployee(Long eid);
    Long managerCount();
    Long employeeCount();

//    String assignDutyToManager(Duty duty, Long managerId);
//    String assignDutyToEmployee(Duty duty, Long employeeId);
    List<Leave> viewAllLeaveApplications();
}
