package com.example.moneytransfer.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    @NotNull
    @Size(min = 3, max = 20)
    private String firstName;

    @NotNull
    @Size(min = 6, max = 30)
    private String lastName;

    @NotNull
    private String username;

    @NotNull
    @Size(min = 8, max = 30)
    private String password;
}
