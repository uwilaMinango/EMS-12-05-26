package com.uwilaminango.employeemanagmentsystem.services.interfaces;

import com.uwilaminango.employeemanagmentsystem.models.Leave;

import java.util.List;

public interface LeaveService {
    Leave applyLeaveByEmployee(Leave leave, Long empId);
    List<Leave> viewLeavesByEmployee(Long empId);
    List<Leave> viewAllPendingLeaves();
    Leave applyLeaveByManager(Leave leave, Long managerId);
    List<Leave> viewLeavesByManager(Long managerId);
    String updateLeaveStatus(Long leaveId, String status);//Accepted or Rejected
}
