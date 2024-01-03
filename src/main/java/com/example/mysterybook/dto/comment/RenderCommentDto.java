package com.example.mysterybook.dto.comment;

import com.example.mysterybook.models.Comments;
import com.example.mysterybook.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RenderCommentDto {
    private int id;
    private int postId;
    private int userId;
    private String content;
    private String creationDate;
    private String username;
    private String fullName;
    private String urlAvatar;

    public void loadFromModel(Comments comment, User userComment) {
        this.id = comment.getId();
        this.postId = comment.getPostId();
        this.userId = comment.getUserId();
        this.content = comment.getContent();
        this.creationDate = comment.getCreationDate();
        this.username = userComment.getUsername();
        this.fullName = userComment.getFullName();
        this.urlAvatar = userComment.getProfilePicture();
    }
}
