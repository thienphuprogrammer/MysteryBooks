package com.example.mysterybook.dto.friend;

import com.example.mysterybook.models.FriendRequest;
import com.example.mysterybook.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RenderFriendRequestDto {
    private int id;
    private int senderId;
    private int receiverId;
    private String fullName;
    private int statusAccept;
    private String avatar;
    public void loadFromModel(FriendRequest friend, User user) {
        this.id = friend.getId();
        this.senderId = friend.getSenderId();
        this.receiverId = friend.getReceiverId();
        this.fullName = user.getFullName();
        this.avatar = user.getProfilePicture();
    }
}
