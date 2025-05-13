package com.myapp.fitnessapplication.controller;

import com.myapp.fitnessapplication.entity.UserDetails;
import com.myapp.fitnessapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserController {
    @Autowired
    UserService userService;

    // get mappings
    @GetMapping("/allusers")
    public List<UserDetails> getAllUsers() {return userService.getAllUsers();}
    @GetMapping("/users/{userId}")
    public UserDetails getUserById(@PathVariable int userId) {return userService.getUserById(userId);}
    // contains
    @GetMapping("/users/contains")
    public List<UserDetails> getUserContainsAge(@RequestParam String name) { return userService.userContainAge(name);}

    // post mappings
    @PostMapping("/adduser")
    public UserDetails addUser(@RequestBody UserDetails userDetails) {return userService.saveUser(userDetails);}
    @PostMapping("/login/")
    public String login(@RequestParam String userEmail, @RequestParam String password) {return userService.authenticateUser(userEmail, password);}

    //put mappings
    @PutMapping("/resetpassword/{userId}")
    public String resetPassword(@PathVariable int userId, @RequestParam String oldPassword, @RequestParam String newPassword) {return userService.resetPassword(userId,oldPassword, newPassword);}
    @PutMapping("/changeusername/{userId}/")
    public String changeUsername(@PathVariable int userId, @RequestParam String username) {return userService.changeUsername(userId,username);}

    //delete mapping
    @DeleteMapping("/deleteuser/{userId}")
    public String deleteUser(@PathVariable int userId) {return userService.deleteUser(userId);}

    // smtp
    @PostMapping("/forgot_password")
    public String sendEmail(@RequestParam String email) {return userService.sendResetPasswordEmail(email);}
}
