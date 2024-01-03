package com.example.mysterybook.dto.user;

import com.example.mysterybook.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateInfoUserDto {
    private int id;
    private String phoneNumber;
    private String dateOfBirth;
    private String address;
    private String bio;
    private String interests;
}
