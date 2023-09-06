package com.example.SpringStart.tables.employee.model;

import com.example.SpringStart.tables.embedded.fileds.Name;
import com.example.SpringStart.tables.logindata.model.LoginData;
import com.example.SpringStart.tables.role.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class EmployeeDTO {

    private Integer id;
    private Name name;
    private Role role;
    private LoginData loginData;
}
