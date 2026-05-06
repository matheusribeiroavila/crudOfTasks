package com.crudoftasks.createTasks.service;

import com.crudoftasks.createTasks.dto.UserDTO;
import com.crudoftasks.createTasks.exception.NotFoundException;
import com.crudoftasks.createTasks.exception.UnauthorizedUserException;
import com.crudoftasks.createTasks.mapper.UserMapper;
import com.crudoftasks.createTasks.model.User;
import com.crudoftasks.createTasks.repository.UserRepository;
import com.crudoftasks.createTasks.security.JwtUtil;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements  IUserService {

    private final JwtUtil jwtUtil;
    UserRepository userRepository;
    UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User registerUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (userRepository.existsUserByEmail(user.getEmail())){
            throw new UnauthorizedUserException("Email do usuário já cadastrado");
        }else if (userRepository.existsUserByUsername(user.getUsername())) {
            throw new UnauthorizedUserException("Username já cadastrado no nosso banco de dados");
        }else if (user.getUserpassword().isEmpty()){
            throw new UnauthorizedUserException("Por favor insira uma senha correta");
        }

        user.setUserpassword(encoder.encode(user.getUserpassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Error 404 | User not found, please review your credentials"));
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public String loginUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User userRepo = userRepository.getUserByUsername(user.getUsername());

        if (userRepo != null && encoder.matches(user.getUserpassword(), userRepo.getUserpassword())) {
            return jwtUtil.generateToken(userRepo.getUsername());
        } else {
            throw new UnauthorizedUserException("User not found, please review your credentials");
        }
    }
}
