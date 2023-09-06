package com.example.SpringStart.tables.employee.service;

import com.example.SpringStart.tables.employee.repository.EmployeeRepository;
import com.example.SpringStart.tables.employee.mapper.EmployeeMapper;
import com.example.SpringStart.tables.employee.model.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public void addEmployee(EmployeeDTO employeeDto) {
        employeeRepository.save(employeeMapper.dtoToEntity(employeeDto));
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        return employeeMapper.entityToDto(employeeRepository.findAll());
    }

}
