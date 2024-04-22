package com.tim.gestionqualite.controller;

import com.tim.gestionqualite.entity.User;
import com.tim.gestionqualite.repository.UserRepository;
import com.tim.gestionqualite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
private UserRepository userRepository;
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElse(null);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        User existingUser = userService.createUser(user);
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setIdUser(id); // Assure que l'id est défini pour la mise à jour
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
