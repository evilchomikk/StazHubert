package org.example;

import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-27T07:29:58+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public EmployeeDTO toDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO.EmployeeDTOBuilder employeeDTO = EmployeeDTO.builder();

        employeeDTO.name( employee.getName() );
        employeeDTO.lastName( employee.getLastName() );
        employeeDTO.salary( employee.getSalary() );

        return employeeDTO.build();
    }
}
