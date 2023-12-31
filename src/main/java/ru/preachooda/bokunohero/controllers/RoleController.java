package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.RoleDto;
import ru.preachooda.bokunohero.entity.Role;
import ru.preachooda.bokunohero.mappers.RoleMapper;
import ru.preachooda.bokunohero.services.RoleService;
import ru.preachooda.bokunoherocore.controllers.BaseEntityController;
import ru.preachooda.bokunoherocore.mappers.BaseEntityMapper;
import ru.preachooda.bokunoherocore.services.BaseEntityService;

@RestController
@RequestMapping("/roles")
public class RoleController extends BaseEntityController<Role, RoleDto> {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseEntityService<Role> getService() {
        return roleService;
    }

    @Override
    public BaseEntityMapper<Role, RoleDto> getMapper() {
        return roleMapper;
    }
}
