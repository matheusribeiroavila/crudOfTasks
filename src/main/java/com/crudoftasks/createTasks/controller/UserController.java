package com.crudoftasks.createTasks.controller;

import com.crudoftasks.createTasks.dto.UserDTO;
import com.crudoftasks.createTasks.mapper.UserMapper;
import com.crudoftasks.createTasks.model.User;
import com.crudoftasks.createTasks.repository.UserRepository;
import com.crudoftasks.createTasks.service.IUserService;
import com.crudoftasks.createTasks.service.UserService;
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
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDto){
        System.out.println("UserDTO senha: "+userDto.userpassword());
        User user = userMapper.toEntity(userDto);
        System.out.println("UserEntity Controller senha: "+user.getUserpassword());
        User createdUser = iUserService.registerUser(user);
        UserDTO response = userMapper.toDTO(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
