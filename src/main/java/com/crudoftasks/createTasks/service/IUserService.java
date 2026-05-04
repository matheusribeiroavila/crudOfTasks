package com.crudoftasks.createTasks.service;

import com.crudoftasks.createTasks.dto.UserDTO;
import com.crudoftasks.createTasks.model.User;
import org.springframework.stereotype.Service;


public interface IUserService {
    public User registerUser(User user);
    public User getUserById(Long userId);
    public User loginUser(User user);
}
