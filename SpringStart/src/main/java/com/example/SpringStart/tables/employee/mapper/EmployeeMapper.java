package com.example.SpringStart.tables.employee.mapper;

import com.example.SpringStart.tables.employee.model.Employee;
import com.example.SpringStart.tables.employee.model.EmployeeDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee dtoToEntity(EmployeeDTO employeeDto);

    EmployeeDTO entityToDto(Employee employee);

    List<EmployeeDTO> entityToDto(List<Employee> employee);

    List<Employee> dtoToEntity(List<EmployeeDTO> employeeDTO);

}
