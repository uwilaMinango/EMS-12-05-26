package com.uwilaminango.employeemanagmentsystem.repository;

import com.uwilaminango.employeemanagmentsystem.models.Duty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DutyRepository extends JpaRepository<Duty, Long> {
}
