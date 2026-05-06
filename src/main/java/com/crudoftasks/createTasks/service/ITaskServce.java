package com.crudoftasks.createTasks.service;

import com.crudoftasks.createTasks.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ITaskServce {

    List<Task> findTasksByUsername(String username);
}
