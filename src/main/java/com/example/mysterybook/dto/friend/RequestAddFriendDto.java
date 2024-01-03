package com.example.mysterybook.dto.friend;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestAddFriendDto {
    private int id;
    private int userId;
    private int senderId;
    private int receiverId;
}
