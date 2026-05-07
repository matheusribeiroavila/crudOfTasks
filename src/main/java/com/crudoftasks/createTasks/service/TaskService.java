package com.crudoftasks.createTasks.service;

import com.crudoftasks.createTasks.dto.CreateTaskDTO;
import com.crudoftasks.createTasks.exception.NotFoundException;
import com.crudoftasks.createTasks.model.Task;
import com.crudoftasks.createTasks.model.User;
import com.crudoftasks.createTasks.repository.TaskRepository;
import com.crudoftasks.createTasks.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskServce{

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskService(UserRepository userRepository, TaskRepository taskRepository){
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findTasksByUsername(String username) {
        User userTarget = userRepository.getUserByUsername(username);
        return userTarget.getTasks();
    }

    @Override
    public Task createTask(CreateTaskDTO createTaskDTO, String username) {
        User user = userRepository.getUserByUsername(username);

        Task taskCreated = new Task();
        taskCreated.setUser(user);
        taskCreated.setTitle(createTaskDTO.title());
        taskCreated.setCompleted(createTaskDTO.completed());
        taskCreated.setDeleted(false);
        taskCreated.setCreateDate(Date.from(Instant.now()));
        taskCreated.setModifyDate(Date.from(Instant.now()));
        taskCreated.setVersion(1);

        return taskRepository.save(taskCreated);
    }

    @Override
    public Task setAsCompleted(Long id) {
        Task taskTarget = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found, please reload de page!"));
        taskTarget.setCompleted(true);
        return taskTarget;
    }
}
