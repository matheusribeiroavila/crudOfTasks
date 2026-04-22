package com.crudoftasks.createTasks.mapper;

import com.crudoftasks.createTasks.dto.UserDTO;
import com.crudoftasks.createTasks.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO dto);
}
