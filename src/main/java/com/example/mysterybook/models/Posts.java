package com.example.mysterybook.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Posts {
    private int id;
    private int userId;
    private int groupId;
    private String title;
    private String content;
    private String creationDate;
    private String visibility;

    public void loadFromDto(com.example.mysterybook.dto.post.UploadPostDto dto) {
        this.id = dto.getId();
        this.userId = dto.getUserId();
        this.groupId = dto.getGroupId();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.visibility = dto.getVisibility();
    }

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", userId=" + userId +
                ", groupId=" + groupId +
                ", content='" + content + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", visibility='" + visibility + '\'' +
                '}';
    }
}
