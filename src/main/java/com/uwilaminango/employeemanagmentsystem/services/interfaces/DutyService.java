package com.uwilaminango.employeemanagmentsystem.services.interfaces;

import com.uwilaminango.employeemanagmentsystem.models.Duty;

import java.util.List;

public interface DutyService {
    Duty assignDutyByAdminToEmployee(Duty duty, Long empId, int adminId);
    Duty assignDutyByAdminToManager(Duty duty, Long managerId, int adminId);
    Duty assignDutyByManagerToEmployee(Duty duty, Long empId, Long managerId);
    List<Duty> viewAllDutiesOfEmployee(Long eId);
    List<Duty> viewDutiesAssignedByManager(Long managerId);
    List<Duty> viewDutiesAssignedByAdmin(int adminId);
}
