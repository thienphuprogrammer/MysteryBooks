package com.example.mysterybook.dto.message;

import com.example.mysterybook.models.Friend;
import com.example.mysterybook.models.Messages;
import com.example.mysterybook.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadMessageDto {
    private int userId;
    private int friendId;
    private String content;
}
