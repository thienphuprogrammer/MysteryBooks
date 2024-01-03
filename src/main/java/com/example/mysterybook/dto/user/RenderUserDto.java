package com.example.mysterybook.dto.user;

import com.example.mysterybook.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RenderUserDto {
    private int id;
    private String fullName;
    private String phoneNumber;
    private String dateOfBirth;
    private String address;
    private String bio;
    private String interests;
    private String profilePicture;
    private String creationDate;
    private int statusRequestFriend;
    public void loadFromModel(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.phoneNumber = user.getPhoneNumber();
        this.dateOfBirth = user.getDateOfBirth();
        this.address = user.getAddress();
        this.bio = user.getBio();
        this.interests = user.getInterests();
        this.profilePicture = user.getProfilePicture();
        this.creationDate = user.getCreationDate();
    }
}
