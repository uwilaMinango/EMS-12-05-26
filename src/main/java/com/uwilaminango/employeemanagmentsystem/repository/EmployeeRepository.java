package com.uwilaminango.employeemanagmentsystem.repository;

import com.uwilaminango.employeemanagmentsystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

   //Search Function
   public List<Employee>findByNameContainingIgnoreCase(String name);

}
