package com.example.mysterybook.services.notifiction;

import com.example.mysterybook.dto.notification.RenderNotification;

import java.util.List;

public interface INotificationService {
    List<RenderNotification> getAllNotifications(int i) throws Exception;
}
