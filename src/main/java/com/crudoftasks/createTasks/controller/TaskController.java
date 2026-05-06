package com.crudoftasks.createTasks.controller;

import com.crudoftasks.createTasks.mapper.UserMapper;
import com.crudoftasks.createTasks.model.Task;
import com.crudoftasks.createTasks.repository.UserRepository;
import com.crudoftasks.createTasks.service.ITaskServce;
import com.crudoftasks.createTasks.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {


    private final ITaskServce iTaskServce;

    public TaskController(ITaskServce iTaskServce){
        this.iTaskServce = iTaskServce;

    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> findAllTasksFromUser(Authentication authentication){
        String username = authentication.getName();
        return ResponseEntity.ok(iTaskServce.findTasksByUsername(username));
    }

}
