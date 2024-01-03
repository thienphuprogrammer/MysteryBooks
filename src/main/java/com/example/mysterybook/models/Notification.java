package com.example.mysterybook.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notification {
    private int id;
    private String type;
    private int SenderId;
    private int ReceiverId;
    private String content;
    private int postId;
    private int isRead;
    private String creationDate;
    private String SeenDate;
}
