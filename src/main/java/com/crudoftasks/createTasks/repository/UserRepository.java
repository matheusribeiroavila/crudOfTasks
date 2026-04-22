package com.crudoftasks.createTasks.repository;

import com.crudoftasks.createTasks.model.User;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {
}
