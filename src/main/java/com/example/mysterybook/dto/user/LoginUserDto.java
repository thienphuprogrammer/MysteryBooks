package com.example.mysterybook.dto.user;

import com.example.mysterybook.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUserDto {
    private String username;
    private String password;
    private String token;
    private String refreshToken;

    public void loadFromModel(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
