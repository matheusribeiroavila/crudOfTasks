package com.crudoftasks.createTasks.controller;

import com.crudoftasks.createTasks.dto.UserDTO;
import com.crudoftasks.createTasks.mapper.UserMapper;
import com.crudoftasks.createTasks.model.User;
import com.crudoftasks.createTasks.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserController(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findAllUsers(@PathVariable Long id){
        User user = userRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(userMapper.toDTO(user));
    }
}
