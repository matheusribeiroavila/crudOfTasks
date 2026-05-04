package com.crudoftasks.createTasks.dto;

import com.crudoftasks.createTasks.model.Task;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public record UserDTO(
        Long id,
        String email,
        String username,
        String userpassword,
        List<Task> tasks,
        String token
) { }
