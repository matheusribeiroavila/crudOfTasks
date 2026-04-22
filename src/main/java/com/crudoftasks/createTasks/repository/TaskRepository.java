package com.crudoftasks.createTasks.repository;

import com.crudoftasks.createTasks.model.Task;
import org.springframework.data.repository.ListCrudRepository;

public interface TaskRepository extends ListCrudRepository<Task, Long> {
}
