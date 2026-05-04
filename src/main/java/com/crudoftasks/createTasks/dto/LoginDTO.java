package com.crudoftasks.createTasks.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank String username,
        @NotBlank String userpassword
) {}
