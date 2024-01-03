package com.example.mysterybook.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordUserDto {
    private int id;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
