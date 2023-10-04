package ru.preachooda.bokunohero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.preachooda.bokunohero.dto.UserDto;
import ru.preachooda.bokunohero.entity.User;
import ru.preachooda.bokunohero.mappers.BaseEntityMapper;
import ru.preachooda.bokunohero.mappers.UserMapper;
import ru.preachooda.bokunohero.services.BaseEntityService;
import ru.preachooda.bokunohero.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController extends BaseEntityController<User, UserDto> {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseEntityService<User> getService() {
        return userService;
    }

    @Override
    public BaseEntityMapper<User, UserDto> getMapper() {
        return userMapper;
    }

}
