package com.crudoftasks.createTasks.mapper;

import com.crudoftasks.createTasks.dto.UserDTO;
import com.crudoftasks.createTasks.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    @Mapping(source = "userpassword", target = "userpassword")
    User toEntity(UserDTO dto);
}
