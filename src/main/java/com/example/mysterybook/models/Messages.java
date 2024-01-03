package com.example.mysterybook.models;

import com.example.mysterybook.dto.message.RenderMessageDto;
import com.example.mysterybook.dto.message.UploadMessageDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Messages {
    private int id;
    private String content;
    private int senderId;
    private int receiverId;
    private String sentDate;

    public void loadFromDto(RenderMessageDto messageFriendDto) {
        this.id = messageFriendDto.getId();
        this.senderId = messageFriendDto.getUserId();
        this.receiverId = messageFriendDto.getFriendId();
    }

    public void loadFromDto(UploadMessageDto uploadMessageDto) {
        this.content = uploadMessageDto.getContent();
        this.senderId = uploadMessageDto.getUserId();
        this.receiverId = uploadMessageDto.getFriendId();
    }
}
