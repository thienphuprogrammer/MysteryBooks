package com.example.mysterybook.models;

import com.example.mysterybook.dto.friend.RequestAddFriendDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendRequest {
    private int id;
    private int senderId;
    private int receiverId;
    private String sentDate;
    private String acceptedDate;
    private boolean accepted;

    public void loadFromDto(RequestAddFriendDto dto) {
        this.id = dto.getId();
        this.senderId = dto.getSenderId();
        this.receiverId = dto.getReceiverId();
    }
}
