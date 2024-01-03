package com.example.mysterybook.models;

import com.example.mysterybook.dto.friend.RequestAddFriendDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Friend {
    private int id;
    private int userId;
    private int friendId;

    public void loadFromDto(RequestAddFriendDto dto) {
        this.userId = dto.getUserId();
        this.friendId = dto.getSenderId();
    }
}
