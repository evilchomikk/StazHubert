package com.example.SpringStart.tables.employee.service;

import com.example.SpringStart.tables.employee.model.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    void addEmployee(EmployeeDTO employeeDto);

    List<EmployeeDTO> getEmployees();
}
