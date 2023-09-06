package com.example.SpringStart.tables.role.controller;

import com.example.SpringStart.tables.role.model.RoleDTO;
import com.example.SpringStart.tables.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/add")
    public void addRole(@RequestBody RoleDTO roleDto) {
        roleService.addRole(roleDto);
    }

    @GetMapping("/get")
    public List<RoleDTO> getRole() {
        return roleService.getRoles();
    }
}
