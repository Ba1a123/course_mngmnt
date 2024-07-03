package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repository.userrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {

    @Autowired
    private userrepo userRepository;

    public boolean authenticateUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            // Directly compare passwords (not secure, but for demonstration purposes)
            return user.getPassword().equals(existingUser.getPassword());
        }
        return false; 
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    public User updateUser(Long id, User userDetails, String role) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getRole().equals(role)) {
            throw new RuntimeException("User role mismatch");
        }
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        // Directly set the password without encoding
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    // public void deleteUser(Long id, String role) {
    //     User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    //     if (!user.getRole().equals(role)) {
    //         throw new RuntimeException("User role mismatch");
    //     }
    //     userRepository.delete(user);
    // }
    public User register(User newUser) {
        // You may want to perform validation or other checks here before saving
        newUser.setPassword(newUser.getPassword());
        return userRepository.save(newUser);
    }
    public User deleteUser(Long id, String role) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getRole().equalsIgnoreCase(role)) {
            throw new RuntimeException("User role mismatch");
        }
        userRepository.delete(user);
        return user;
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
