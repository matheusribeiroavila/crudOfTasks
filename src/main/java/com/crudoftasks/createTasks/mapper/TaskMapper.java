package com.crudoftasks.createTasks.mapper;

import com.crudoftasks.createTasks.dto.TaskDTO;
import com.crudoftasks.createTasks.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO toDto(Task task);
    Task toEntity(TaskDTO dto);
}
