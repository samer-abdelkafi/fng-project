package com.mycompany.myproject.web.controller;

import com.mycompany.myproject.service.UserService;
import com.mycompany.myproject.service.dto.UserDto;
import io.swagger.annotations.Api;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "Users management API")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource ms;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<UserDto> usersList() {
        logger.debug("get users list");
        return userService.findAll();
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public @ResponseBody UserDto getUser(@PathVariable Long userId) {
        logger.debug("get user");
        return userService.findOne(userId);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public @ResponseBody UserDto saveUser(@RequestBody UserDto user) {
        logger.debug("save user");
        userService.save(user);
        return user;
    }





}

 
