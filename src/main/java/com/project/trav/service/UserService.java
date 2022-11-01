package com.project.trav.service;

import com.project.trav.mapper.UserMapper;
import com.project.trav.model.dto.UserDto;
import com.project.trav.model.entity.Status;
import com.project.trav.model.entity.User;
import com.project.trav.repository.UserRepository;
import com.project.trav.exeption.EntityNotFoundByIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String NOT_FOUND_ERROR = "User was not found";
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getUsers(){
        return userMapper.toUserDtos(userRepository.findAll());
    }
    public UserDto getUser(Long id){
        return userMapper.toUserDto(findByIdUser(id));
    }
    public void addUser(UserDto userDto){userRepository.save(userMapper.toUser(userDto));}
    public void updateUser(UserDto userDto, Long id){
        existByIdUser(id);
        userRepository.save(userMapper.toUser(userDto));
    }
    public void deleteUser(Long id){
        existByIdUser(id);
        userRepository.deleteById(id);
    }
    public void deactivateUser(Long id){
        User user = findByIdUser(id);
        user.setStatus(Status.BANNED);
        updateUser(userMapper.toUserDto(user),id);
    }
    private User findByIdUser(Long id) {
        return userRepository.findById(id).orElseThrow(()->{
            throw new EntityNotFoundByIdException(NOT_FOUND_ERROR);
        });

    }
    private void existByIdUser(Long id){
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundByIdException(NOT_FOUND_ERROR);
        }
    }
}