package com.example.mysterybook.models;

import com.example.mysterybook.dto.image.UploadImageDto;
import com.example.mysterybook.dto.post.UploadPostDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Images {
    private int id;
    private int postId;
    private int userId;
    private String urlImg;

    public void loadFromDto(UploadPostDto dto) {
        this.id = dto.getId();
        this.postId = dto.getId();
        this.userId = dto.getUserId();
        this.urlImg = dto.getUrlImages().get(0);
    }
    public void loadFromDto(UploadImageDto dto) {
        this.postId = dto.getPostId();
        this.userId = dto.getUserId();
        this.id = dto.getId();
    }
}
