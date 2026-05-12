package com.uwilaminango.employeemanagmentsystem.repository;

import com.uwilaminango.employeemanagmentsystem.models.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
}
