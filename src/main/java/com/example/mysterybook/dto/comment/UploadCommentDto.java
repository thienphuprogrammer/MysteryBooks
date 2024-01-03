package com.example.mysterybook.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadCommentDto {
    int userId;
    int postId;
    String content;
}
