package com.example.mysterybook.models;

import com.example.mysterybook.dto.like.UploadEmotion;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Emotion {
    private int id;
    private int postId;
    private int userId;
    private String type;
    private String creationDate;

    public void loadFromDto(UploadEmotion dto) {
        this.postId = dto.getPostId();
        this.userId = dto.getUserId();
        this.type = dto.getType();
    }
}
