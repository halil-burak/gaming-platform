package com.turkcell.playcell.gamingplatform.cms.service.impl;

import com.turkcell.playcell.gamingplatform.cms.dto.user.UserCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.user.UserGetDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.user.UserLoginDTO;
import com.turkcell.playcell.gamingplatform.cms.exception.InvalidPasswordException;
import com.turkcell.playcell.gamingplatform.cms.exception.NotFoundException;
import com.turkcell.playcell.gamingplatform.cms.security.JwtProvider;
import com.turkcell.playcell.gamingplatform.cms.service.UserService;
import com.turkcell.playcell.gamingplatform.common.entity.User;
import com.turkcell.playcell.gamingplatform.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtProvider jwtProvider;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(UserLoginDTO userLoginDTO) throws InvalidPasswordException {
        User user = userRepository.findByUsername(userLoginDTO.getUsername()).orElseThrow( () -> new NotFoundException(User.class, userLoginDTO.getUsername()));
        CharBuffer psw = CharBuffer.wrap(userLoginDTO.getPassword());
        if(!passwordEncoder.matches(psw, user.getPassword())){
            Arrays.fill(psw.array(), '0');
            throw new InvalidPasswordException("Invalid Password");
        }
        String token = jwtProvider.generateJwtToken();
        user.setToken(token);
        userLoginDTO.finalize();
        Arrays.fill(psw.array(), '0');
        return token;
    }

    @Override
    public UserGetDTO init(String username) {
        User user = userRepository.findByUsername(username).orElseThrow( () -> new NotFoundException(User.class, username));
        return modelMapper.map(user, UserGetDTO.class);
    }

    @Override
    public Long saveUser(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        CharBuffer psw = CharBuffer.wrap(userCreateDTO.getPassword());
        user.setPassword(passwordEncoder.encode(psw));
        user.setRole(userCreateDTO.getRole());
        user.setIsactive(userCreateDTO.getIsactive());
        userCreateDTO.finalize();
        Arrays.fill(psw.array(), '0');
        return userRepository.save(user).getId();
    }

    @Override
    public void updateUser(Long id, UserCreateDTO userCreateDTO) {
        User user = userRepository.findById(id).orElseThrow( () -> new NotFoundException(User.class, id));
        user.setUsername(userCreateDTO.getUsername());
        CharBuffer psw = CharBuffer.wrap(userCreateDTO.getPassword());
        user.setPassword(passwordEncoder.encode(psw));
        user.setRole(userCreateDTO.getRole());
        user.setIsactive(userCreateDTO.getIsactive());
        userCreateDTO.finalize();
        Arrays.fill(psw.array(), '0');
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserGetDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map( t-> modelMapper.map(t, UserGetDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByToken(String token)  {

        User user = userRepository.findByToken(token).orElseThrow(()-> new NotFoundException("Unable to find User with token"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    @Override
    public void logout(String username) {
        User user = userRepository.findByUsername(username).orElseThrow( () -> new NotFoundException(User.class, username));
        user.setToken(null);
    }
}
