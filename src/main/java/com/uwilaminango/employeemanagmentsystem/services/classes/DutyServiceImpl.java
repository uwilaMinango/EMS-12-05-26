package com.uwilaminango.employeemanagmentsystem.services.classes;

import com.uwilaminango.employeemanagmentsystem.models.Admin;
import com.uwilaminango.employeemanagmentsystem.models.Duty;
import com.uwilaminango.employeemanagmentsystem.models.Employee;
import com.uwilaminango.employeemanagmentsystem.models.Manager;
import com.uwilaminango.employeemanagmentsystem.repository.AdminRepository;
import com.uwilaminango.employeemanagmentsystem.repository.DutyRepository;
import com.uwilaminango.employeemanagmentsystem.repository.EmployeeRepository;
import com.uwilaminango.employeemanagmentsystem.repository.ManagerRepository;
import com.uwilaminango.employeemanagmentsystem.services.interfaces.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DutyServiceImpl implements DutyService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private DutyRepository dutyRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Duty assignDutyByAdminToEmployee(Duty duty, Long empId, int adminId) {
        Employee emp = employeeRepository.findById(empId).orElse(null);
        Admin admin = adminRepository.findById(adminId).orElse(null);

        if(emp != null && admin != null){
            duty.setEmployee(emp);
            duty.setAssignedByAdmin(admin);
            return dutyRepository.save(duty);
        }
        return null;
    }

    @Override
    public Duty assignDutyByAdminToManager(Duty duty, Long managerId, int adminId) {
        Manager manager = managerRepository.findById(managerId).orElse(null);
        Admin admin = adminRepository.findById(adminId).orElse(null);

        if(manager != null && admin != null){
            duty.setEmployee(null);
            duty.setManager(manager);
            duty.setAssignedByAdmin(admin);
            return dutyRepository.save(duty);
        }
        return null;
    }

    @Override
    public Duty assignDutyByManagerToEmployee(Duty duty, Long empId, Long managerId) {
        Employee emp = employeeRepository.findById(empId).orElse(null);
        Manager manager = managerRepository.findById(managerId).orElse(null);

        if(emp != null && manager != null){
            duty.setEmployee(emp);
            duty.setAssignedByManager(manager);
            return dutyRepository.save(duty);
        }
        return null;
    }

    @Override
    public List<Duty> viewAllDutiesOfEmployee(Long eId) {
        return dutyRepository.findByEmployeeId(eId);
    }

    @Override
    public List<Duty> viewDutiesAssignedByManager(Long managerId) {
        return dutyRepository.findByAssignedByManagerId(managerId);
    }

    @Override
    public List<Duty> viewDutiesAssignedByAdmin(int adminId) {
        return dutyRepository.findByAssignedByAdminId(adminId);
    }
}
