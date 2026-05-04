package com.crudoftasks.createTasks.repository;

import com.crudoftasks.createTasks.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User, Long> {
    boolean existsUserByEmail(String email);
    boolean existsUserByUsername(String username);

    User getUserByUsername(String username);
}
