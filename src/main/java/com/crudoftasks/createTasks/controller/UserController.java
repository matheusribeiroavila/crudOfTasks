package com.crudoftasks.createTasks.controller;

import com.crudoftasks.createTasks.dto.CreateUserDTO;
import com.crudoftasks.createTasks.dto.LoginDTO;
import com.crudoftasks.createTasks.dto.TokenDTO;
import com.crudoftasks.createTasks.dto.UserDTO;
import com.crudoftasks.createTasks.mapper.UserMapper;
import com.crudoftasks.createTasks.model.User;
import com.crudoftasks.createTasks.repository.UserRepository;
import com.crudoftasks.createTasks.service.IUserService;
import com.crudoftasks.createTasks.service.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserMapper userMapper;
    private final IUserService iUserService;

    public UserController(UserMapper userMapper, IUserService iUserService){
        this.userMapper = userMapper;
        this.iUserService = iUserService;
    }


    @PostMapping("/user/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody CreateUserDTO userDto){
        User user = userMapper.toCreateEntity(userDto);
        User createdUser = iUserService.registerUser(user);
        UserDTO response = userMapper.toDTO(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<TokenDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO){
        User user = userMapper.toLoginEntity(loginDTO);
        String token = iUserService.loginUser(user);

        return ResponseEntity.ok().body(new TokenDTO(token));
    }

    @GetMapping("/finduser/{id}")
    public ResponseEntity<UserDTO> findUserById(@Valid @PathVariable Long id){
        User user = iUserService.getUserById(id);
        UserDTO response = userMapper.toDTO(user);
        return ResponseEntity.ok().body(response);
    }
}
