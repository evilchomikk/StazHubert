package com.example.SpringStart.tables.employee.controller;

import com.example.SpringStart.commons.dto.employee.EmployeeDTO;
import com.example.SpringStart.tables.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/add")
    public void addEmployee(@RequestBody EmployeeDTO employeeDto) {
        employeeService.addEmployee(employeeDto);
    }

    @PostMapping("/get")
    public void getEmployees() {
        employeeService.getEmployees();
    }
}
