package com.crudoftasks.createTasks.service;

import com.crudoftasks.createTasks.dto.CreateTaskDTO;
import com.crudoftasks.createTasks.dto.TaskDTO;
import com.crudoftasks.createTasks.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ITaskServce {

    List<Task> findTasksByUsername(String username);
    Task createTask(CreateTaskDTO createTaskDTO, String username);
    Task setAsCompleted(Long id);
    Task setAsUncompleted(Long id);
}
