package com.example.mysterybook.dto.notification;

import com.example.mysterybook.models.Notification;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RenderNotification {
    private int id;
    private String type;
    private int SenderId;
    private int ReceiverId;
    private String content;
    private int isRead;
    private String creationDate;
    private String SeenDate;
    private String link;
    private int postId;

    public void loadFromModel(Notification notification) {
        this.id = notification.getId();
        this.type = notification.getType();
        this.SenderId = notification.getSenderId();
        this.ReceiverId = notification.getReceiverId();
        this.content = notification.getContent();
        this.isRead = notification.getIsRead();
        this.creationDate = notification.getCreationDate();
        this.SeenDate = notification.getSeenDate();
        this.postId = notification.getPostId();
    }
}
