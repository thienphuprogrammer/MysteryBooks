package com.example.mysterybook.dto.post;

import com.example.mysterybook.dto.comment.RenderCommentDto;
import com.example.mysterybook.models.Comments;
import com.example.mysterybook.models.Images;
import com.example.mysterybook.models.Posts;
import com.example.mysterybook.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RenderPostDto {
    public enum VISIBILITY {
        PUBLIC,
        FRIENDS,
        ONLY_ME
    }

    private int id;
    private String urlAvatar;
    private int userId;
    private int groupId;
    private String username;
    private String fullName;
    private String content;
    private String title;
    private List<Images> images;
    private long numberOfLikes;
    private List<RenderCommentDto> comments;
    private long numberOfComments;
    private long numberOfViews;
    private long numberOfShares;
    private String visibility;
    private String creationDate;
    private int isLiked;
    
    public void loadFromModel(Posts post, List<Images> images, User user, List<Comments> comments, List<RenderCommentDto> renderCommentDto) {
        this.id = post.getId();
        this.userId = post.getUserId();
        this.groupId = post.getGroupId();
        this.urlAvatar = user.getProfilePicture();
        this.content = post.getContent();
        this.title = post.getTitle();
        this.visibility = post.getVisibility();
        this.creationDate = post.getCreationDate();
        this.images = images;
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.comments = renderCommentDto;
        this.numberOfComments = comments.size();
    }
}
