package com.example.mysterybook.dto.message;

import com.example.mysterybook.models.Friend;
import com.example.mysterybook.models.Messages;
import com.example.mysterybook.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageFriendsDto {
    private int id;
    private int userId;
    private int friendId;
    private String content;
    private String sentDate;
    private String fullName;
    private String avatar;

    public void loadFromModel(Friend friend, User friendUser, Messages friendsMessage) {
        this.id = friendsMessage.getId();
        this.userId = friend.getUserId();
        this.friendId = friend.getFriendId();
        this.content = friendsMessage.getContent();
        this.sentDate = friendsMessage.getSentDate();
        this.fullName = friendUser.getFullName();
        this.avatar = friendUser.getProfilePicture();
    }
}
