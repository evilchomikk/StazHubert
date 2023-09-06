package com.example.SpringStart.tables.role.mapper;

import com.example.SpringStart.tables.role.model.Role;
import com.example.SpringStart.tables.role.model.RoleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role dtoToEntity(RoleDTO roleDto);

    RoleDTO entityToDto(Role role);

    List<RoleDTO> entityToDto(List<Role> role);

    List<Role> dtoToEntity(List<RoleDTO> roleDto);
}
