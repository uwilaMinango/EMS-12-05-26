package com.uwilaminango.employeemanagmentsystem.repository;

import com.uwilaminango.employeemanagmentsystem.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
