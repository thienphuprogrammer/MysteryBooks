package com.example.mysterybook.services.notifiction;

import com.example.mysterybook.daos.DaoFactory;
import com.example.mysterybook.daos.notification.INotificationDao;
import com.example.mysterybook.dto.notification.RenderNotification;
import com.example.mysterybook.models.Notification;
import com.example.mysterybook.models.Posts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotificationService implements INotificationService {
    private static NotificationService instance = null;
    INotificationDao notificationDao = null;

    private NotificationService() throws Exception {
        notificationDao = DaoFactory.getInstance().getNotificationDao();
    }

    public static NotificationService getInstance() throws Exception {
        if (instance == null) {
            instance = new NotificationService();
        }

        return instance;
    }

    @Override
    public List<RenderNotification> getAllNotifications(int i) throws Exception {
        List<RenderNotification> result = new ArrayList<>();
        try {
            List<Notification> notifications = notificationDao.getAllNotifications(i);
            for (Notification notification : notifications) {
                RenderNotification notificationDto = new RenderNotification();
                notificationDto.loadFromModel(notification);
                result.add(notificationDto);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return result;
    }
}
