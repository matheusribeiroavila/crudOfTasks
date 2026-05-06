package com.crudoftasks.createTasks.service;

import com.crudoftasks.createTasks.model.Task;
import com.crudoftasks.createTasks.model.User;
import com.crudoftasks.createTasks.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskServce{

    private final UserRepository userRepository;

    public TaskService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<Task> findTasksByUsername(String username) {
        User userTarget = userRepository.getUserByUsername(username);
        return userTarget.getTasks();
    }
}
