package com.crudoftasks.createTasks.dto;

import com.crudoftasks.createTasks.model.User;

import java.util.Date;

public record TaskDTO(Long id, User user, String title, Boolean completed, Boolean deleted, Date createDate, Date modifyDate, Integer version) {
}
