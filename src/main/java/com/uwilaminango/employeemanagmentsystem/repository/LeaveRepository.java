package com.uwilaminango.employeemanagmentsystem.repository;

import com.uwilaminango.employeemanagmentsystem.models.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findByEmployeeId(Long eId);
    List<Leave> findByStatus(String status);
    List<Leave> findByManagerId(Long mId);
}
