package com.crudoftasks.createTasks.controller;

import com.crudoftasks.createTasks.dto.CreateUserDTO;
import com.crudoftasks.createTasks.dto.LoginDTO;
import com.crudoftasks.createTasks.dto.UserDTO;
import com.crudoftasks.createTasks.mapper.UserMapper;
import com.crudoftasks.createTasks.model.User;
import com.crudoftasks.createTasks.repository.UserRepository;
import com.crudoftasks.createTasks.service.IUserService;
import com.crudoftasks.createTasks.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserMapper userMapper;
    private final IUserService iUserService;

    public UserController(UserMapper userMapper, IUserService iUserService){
        this.userMapper = userMapper;
        this.iUserService = iUserService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(
                userMapper.toDTO(iUserService.getUserById(id))
        );
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody CreateUserDTO userDto){
        User user = userMapper.toCreateEntity(userDto);
        User createdUser = iUserService.registerUser(user);
        UserDTO response = userMapper.toDTO(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO){
        User user = userMapper.toLoginEntity(loginDTO);
        User loggedUser = iUserService.loginUser(user);
        UserDTO response = userMapper.toDTO(loggedUser);

        return ResponseEntity.ok().body(response);
    }
}
