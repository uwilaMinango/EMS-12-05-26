package com.uwilaminango.employeemanagmentsystem.repository;

import com.uwilaminango.employeemanagmentsystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

   //Search Function
   Employee findByUsernameAndPassword (String username, String Password);
   Employee findByUsername (String username);
   Employee findByEmail (String email);
   Optional<Employee> FindByEmail(String email);
}
