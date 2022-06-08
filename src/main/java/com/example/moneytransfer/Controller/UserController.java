package com.example.moneytransfer.Controller;

import com.example.moneytransfer.DTO.ApiResponse;
import com.example.moneytransfer.DTO.UserDTO;
import com.example.moneytransfer.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping(value = "/register")
    public HttpEntity<?> registerUser(@RequestBody UserDTO userDTO){
        ApiResponse apiResponse = userService.registerUser(userDTO);
        return ResponseEntity.status(apiResponse.getType()?201:409).body(apiResponse.getMessage());
    }
}