package com.example.SpringStart.tables.role.service;

import com.example.SpringStart.tables.role.mapper.RoleMapper;
import com.example.SpringStart.tables.role.model.RoleDTO;
import com.example.SpringStart.tables.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public void addRole(RoleDTO roleDto) {
        roleRepository.save(roleMapper.dtoToEntity(roleDto));
    }

    @Override
    public List<RoleDTO> getRoles() {
        return roleMapper.entityToDto(roleRepository.findAll());
    }

}
