package com.example.mysterybook.daos.notification;

import com.example.mysterybook.models.Messages;
import com.example.mysterybook.models.Notification;

import java.util.List;

public interface INotificationDao {
    List<Notification> getAllNotifications(int i) throws Exception;
}
