package com.uwilaminango.employeemanagmentsystem.repository;

import com.uwilaminango.employeemanagmentsystem.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByUsernameAndPassword(String username, String password);

    Manager findByUsername(String username);

    Manager findByEmail(String email);

    Optional<Manager> FindByEmail(String email);
}
