package com.example.mysterybook.dto.message;

import com.example.mysterybook.models.Messages;
import com.example.mysterybook.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RenderMessageDto {
    private int id;
    private String content;
    private int userId;
    private int friendId;
    private String sentDate;
    private String avatar;
    private String fullName;
    private int isFriend;

    public void loadFromModel(Messages message, User user) {
        this.id = message.getId();
        this.content = message.getContent();
        this.userId = message.getSenderId();
        this.friendId = message.getReceiverId();
        this.sentDate = message.getSentDate();
        this.fullName = user.getFullName();
        this.avatar = user.getProfilePicture();
    }
}
