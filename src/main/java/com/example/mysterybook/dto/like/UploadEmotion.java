package com.example.mysterybook.dto.like;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UploadEmotion {
    private int userId;
    private int postId;
    private int groupId;
    private int commentId;
    private String type;
}
