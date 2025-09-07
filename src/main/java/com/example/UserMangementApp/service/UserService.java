package com.example.UserMangementApp.service;

import com.example.UserMangementApp.Repository.UserRepository;
import com.example.UserMangementApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repo;

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public ResponseEntity<User> getUserById(long id) {
        User user = repo.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    public ResponseEntity<User> createUser(User user) {
        User savedUser = repo.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    public ResponseEntity<User> updateUser(Long id, User user) {
        User currentUser = repo.findById(id).orElse(null);
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(user.getPassword());
        User updatedUser = repo.save(currentUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteUser(Long id) {
        User user = repo.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repo.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
