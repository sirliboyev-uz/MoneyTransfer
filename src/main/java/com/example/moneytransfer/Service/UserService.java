package com.example.moneytransfer.Service;

import com.example.moneytransfer.DTO.ApiResponse;
import com.example.moneytransfer.DTO.UserDTO;
import com.example.moneytransfer.Model.Users;
import com.example.moneytransfer.Repository.RoleRepository;
import com.example.moneytransfer.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static com.example.moneytransfer.Enums.RoleName.USER;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;


    public ApiResponse registerUser(UserDTO userDTO){
        Optional<Users> usersOptional = userRepository.findByUsername(userDTO.getUsername());
        if (usersOptional.isPresent()){
            return new ApiResponse("Already user exist", false);
        }
        Users users = new Users();
        users.setFirstName(userDTO.getFirstName());
        users.setLastName(userDTO.getLastName());
        users.setUsername(userDTO.getUsername());
        users.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        users.setRole(Collections.singletonList(roleRepository.findByRoleName(USER)));
        userRepository.save(users);
        return new ApiResponse("User successfully registered", true);
    }
}
