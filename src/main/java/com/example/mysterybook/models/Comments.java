package com.example.mysterybook.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Comments {
    private int id;
    private int postId;
    private int userId;
    private String content;
    private String creationDate;
}
