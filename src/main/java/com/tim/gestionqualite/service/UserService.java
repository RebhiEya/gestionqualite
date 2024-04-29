package com.tim.gestionqualite.service;

import com.tim.gestionqualite.entity.User;
import com.tim.gestionqualite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public List<User> deleteUser(Long userid) {
        if (!userRepository.existsById(userid)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(userid);
        return userRepository.findAll();
    }


    public User createdUser(User user) {
        return userRepository.save(user);
    }
}
