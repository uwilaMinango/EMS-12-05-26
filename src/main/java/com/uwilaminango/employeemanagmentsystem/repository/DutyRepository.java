package com.uwilaminango.employeemanagmentsystem.repository;

import com.uwilaminango.employeemanagmentsystem.models.Duty;
import com.uwilaminango.employeemanagmentsystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DutyRepository extends JpaRepository<Duty, Long> {
    List<Duty> findByEmployee(Employee employee);
    List<Duty> findByEmployeeId(Long id);
    List<Duty> findByAssignedByManagerId(Long id);
    List<Duty> findByAssignedByAdminId(int id);
}
