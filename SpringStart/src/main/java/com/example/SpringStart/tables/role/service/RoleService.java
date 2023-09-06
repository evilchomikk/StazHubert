package com.example.SpringStart.tables.role.service;

import com.example.SpringStart.tables.role.model.RoleDTO;

import java.util.List;

public interface RoleService {

    void addRole(RoleDTO roleDto);

    List<RoleDTO> getRoles();

}
