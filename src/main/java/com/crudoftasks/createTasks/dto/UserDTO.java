package com.crudoftasks.createTasks.dto;

import com.crudoftasks.createTasks.model.Task;

import java.util.List;

public record UserDTO(Long id, String email, String username, String password, List<Task> tasks, String token) {
}
