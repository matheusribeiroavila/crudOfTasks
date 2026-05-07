package com.crudoftasks.createTasks.controller;

import com.crudoftasks.createTasks.dto.CreateTaskDTO;
import com.crudoftasks.createTasks.dto.TaskDTO;
import com.crudoftasks.createTasks.mapper.TaskMapper;
import com.crudoftasks.createTasks.mapper.UserMapper;
import com.crudoftasks.createTasks.model.Task;
import com.crudoftasks.createTasks.model.User;
import com.crudoftasks.createTasks.repository.UserRepository;
import com.crudoftasks.createTasks.service.ITaskServce;
import com.crudoftasks.createTasks.service.IUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {


    private final TaskMapper taskMapper;
    private final ITaskServce iTaskServce;
    private final IUserService iUserService;

    public TaskController(TaskMapper taskMapper, ITaskServce iTaskServce, IUserService iUserService){
        this.taskMapper = taskMapper;
        this.iTaskServce = iTaskServce;
        this.iUserService = iUserService;

    }

    @GetMapping()
    public ResponseEntity<List<Task>> findAllTasksFromUser(Authentication authentication){
        String username = authentication.getName();
        return ResponseEntity.ok(iTaskServce.findTasksByUsername(username));
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDTO> createNewTask(@Valid @RequestBody CreateTaskDTO createTaskDTO, Authentication authentication){
        System.out.println("acessou endpoint");
        String username = authentication.getName();


        Task taskCreated = iTaskServce.createTask(createTaskDTO, username);
        TaskDTO response = taskMapper.toDto(taskCreated);

        System.out.println(response.toString());
        return ResponseEntity.status(201).body(response);

    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<TaskDTO> setAsCompleted(@Valid @PathVariable Long id){
        return ResponseEntity.ok().body(taskMapper.toDto(iTaskServce.setAsCompleted(id)));
    }

}
