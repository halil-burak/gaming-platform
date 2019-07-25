package com.turkcell.playcell.gamingplatform.cms.service;

import com.turkcell.playcell.gamingplatform.cms.dto.user.UserCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.user.UserGetDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.user.UserLoginDTO;
import com.turkcell.playcell.gamingplatform.cms.exception.LdapAuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    String login(UserLoginDTO userLoginDTO) throws LdapAuthenticationException;

    UserGetDTO init(String username);

//    Long saveUser(UserCreateDTO userCreateDTO);
//
//    void updateUser(Long id, UserCreateDTO userCreateDTO);
//
//    void deleteUser(Long id);
//
//    List<UserGetDTO> getUsers();

    UserDetails loadUserByToken(String token);

    void logout(String username);
}
