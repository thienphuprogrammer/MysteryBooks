package com.example.mysterybook.models;

import com.example.mysterybook.dto.user.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String username;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String dateOfBirth;
    private String address;
    private String bio;
    private String interests;
    private String profilePicture;
    private String creationDate;
    private String lastLoginDate;
    private String password;
    public User() {
    }

    public void loadFromDto(RegisterUserDto userDto) {
        this.username = userDto.getUsername();
        this.fullName = userDto.getFullName();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.phoneNumber = userDto.getPhoneNumber();
        this.address = userDto.getAddress();
    }

    public void loadFromDto(LoginUserDto userDto) {
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
    }

    public void loadFromDto(UpdateInfoUserDto userDto) {
        this.id = userDto.getId();
        this.phoneNumber = userDto.getPhoneNumber();
        this.dateOfBirth = userDto.getDateOfBirth();
        this.address = userDto.getAddress();
        this.bio = userDto.getBio();
        this.interests = userDto.getInterests();
    }

    public void loadFromDto(UpdateFullNameUserDto userDto) {
        this.id = userDto.getId();
        this.fullName = userDto.getFullName();
    }

    public void loadFromDto(UpdatePasswordUserDto userDto) {
        this.id = userDto.getId();
        this.password = userDto.getNewPassword();
    }

    public void loadFromDto(UpdateAvatarUserDto userDto) {
        this.id = userDto.getId();
        this.profilePicture = userDto.getAvatar();
    }
}
