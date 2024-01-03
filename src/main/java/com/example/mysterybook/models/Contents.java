package com.example.mysterybook.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Contents {
    private int id;
    private int postId;
    private int userId;
    private String content;
    private String title;
    private String visibility;

}
