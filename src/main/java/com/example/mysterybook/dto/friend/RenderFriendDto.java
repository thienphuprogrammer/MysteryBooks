package com.example.mysterybook.dto.friend;

import com.example.mysterybook.models.Friend;
import com.example.mysterybook.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RenderFriendDto {
    private int id;
    private int userId;
    private int friendId;
    private String fullName;
    private String friendAvatar;

    public void loadFromModel(Friend friend, User user) {
        this.id = friend.getId();
        this.userId = friend.getUserId();
        this.friendId = friend.getFriendId();
        this.fullName = user.getFullName();
        this.friendAvatar = user.getProfilePicture();
    }
}
