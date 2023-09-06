package com.example.SpringStart.tables.employee.repository;

import com.example.SpringStart.tables.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
