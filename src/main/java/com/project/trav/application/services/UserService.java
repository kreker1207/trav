package com.project.trav.application.services;

import com.project.trav.domain.entity.User;
import com.project.trav.domain.repository.UserRepository;
import com.project.trav.exeption.EntityNotFoundByIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String NOT_FOUND_ERROR = "User was not found";
    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(()->{
            throw new EntityNotFoundByIdException(NOT_FOUND_ERROR);
        });
    }
    public void addUser(User user){
        userRepository.save(user);
    }
    public void updateUser(User user, Long id){
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundByIdException(NOT_FOUND_ERROR);
        }
        userRepository.save(user);
    }
    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundByIdException(NOT_FOUND_ERROR);
        }
        userRepository.deleteById(id);
    }
}
