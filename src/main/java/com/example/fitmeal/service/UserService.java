package com.example.fitmeal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fitmeal.entity.User;
import com.example.fitmeal.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User  findById(Long id){
        return userRepository.findById(id).get();
    }
    
}
