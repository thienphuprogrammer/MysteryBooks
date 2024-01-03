package com.example.mysterybook.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class UploadPostDto {
    public enum VISIBILITY {
        PUBLIC,
        FRIENDS,
        ONLY_ME
    }
    private int id;
    private int userId;
    private int groupId;
    private String title;
    private String content;
    private ArrayList<String> urlImages;
    private String visibility;
}
