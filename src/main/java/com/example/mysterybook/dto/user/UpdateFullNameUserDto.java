package com.example.mysterybook.dto.user;

import com.example.mysterybook.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFullNameUserDto {
    private int id;
    private String fullName;
}
