package com.uwilaminango.employeemanagmentsystem.services.classes;

import com.uwilaminango.employeemanagmentsystem.models.Employee;
import com.uwilaminango.employeemanagmentsystem.models.Leave;
import com.uwilaminango.employeemanagmentsystem.models.Manager;
import com.uwilaminango.employeemanagmentsystem.repository.EmployeeRepository;
import com.uwilaminango.employeemanagmentsystem.repository.LeaveRepository;
import com.uwilaminango.employeemanagmentsystem.repository.ManagerRepository;
import com.uwilaminango.employeemanagmentsystem.services.interfaces.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LeaveRepository leaveRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Leave applyLeaveByEmployee(Leave leave, Long empId) {
        Employee emp = employeeRepository.findById(empId).orElse(null);
        if(emp != null){
            leave.setEmployee((emp));
            leave.setStatus("PENDING");
            return leaveRepository.save(leave);
        }
        return null;
    }

    @Override
    public List<Leave> viewLeavesByEmployee(Long empId) {
        return leaveRepository.findByEmployeeId(empId);
    }

    @Override
    public List<Leave> viewAllPendingLeaves() {
        return leaveRepository.findByStatus("PENDING");
    }

    @Override
    public Leave applyLeaveByManager(Leave leave, Long managerId) {
        Manager manager = managerRepository.findById(managerId).orElse(null);
        if(manager != null){
            leave.setManager((manager));
            leave.setStatus("PENDING");
            return leaveRepository.save(leave);
        }
        return null;
    }

    @Override
    public List<Leave> viewLeavesByManager(Long managerId) {
        return leaveRepository.findByManagerId(managerId);
    }

    @Override
    public String updateLeaveStatus(Long leaveId, String status) {
        Leave leave = leaveRepository.findById(leaveId).orElse(null);

        if(leave != null){
            leave.setStatus(status.toUpperCase());
            return "Leave Status Updated to: " + status;
        }
        return "Leave ID not found";
    }
}
