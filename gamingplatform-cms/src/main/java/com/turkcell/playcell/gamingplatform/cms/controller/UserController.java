package com.turkcell.playcell.gamingplatform.cms.controller;

import com.turkcell.playcell.gamingplatform.cms.dto.user.UserCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.user.UserGetDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.user.UserLoginDTO;
import com.turkcell.playcell.gamingplatform.cms.exception.InvalidPasswordException;
import com.turkcell.playcell.gamingplatform.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(path = "/login")
    public String login(@RequestBody @Valid UserLoginDTO userLoginDTO) throws InvalidPasswordException {
        return userService.login(userLoginDTO);
    }

    @PostMapping(path = "/removeSession")
    public void logout(Principal principal) {
        String username = principal.getName();
        userService.logout(username);
    }

    @GetMapping(path="/init")
    public UserGetDTO init(Principal principal) {
        String username = principal.getName();
        return userService.init(username);
    }

    @PostMapping(path= "/users")
    Long saveUser(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        return userService.saveUser(userCreateDTO);
    }

    @PutMapping(path= "/users/{id}")
    public void updateUser(@PathVariable(name = "id") Long id, @RequestBody @Valid UserCreateDTO userCreateDTO) {
        userService.updateUser(id, userCreateDTO);
    }

    @DeleteMapping(path="/users/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
    }

    @GetMapping(path= "/users")
    public List<UserGetDTO> getUsers() {
        return userService.getUsers();
    }

}
