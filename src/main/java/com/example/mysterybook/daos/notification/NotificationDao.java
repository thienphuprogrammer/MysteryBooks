package com.example.mysterybook.daos.notification;

import com.example.mysterybook.models.Messages;
import com.example.mysterybook.models.Notification;
import com.example.mysterybook.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationDao implements INotificationDao {
    private static Connection connection = null;

    public NotificationDao() throws Exception {
        connection = DatabaseUtil.getInstance().getConnection();
    }

    @Override
    public List<Notification> getAllNotifications(int i) throws Exception {
        List<Notification> result = new ArrayList<>();
        try {
            String query = """
                    SELECT * FROM notifications
                    WHERE receiver_id = ?
                    ORDER BY creation_date DESC
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, i);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Notification notification = new Notification();
                notification.setId(resultSet.getInt("id"));
                notification.setSenderId(resultSet.getInt("sender_id"));
                notification.setReceiverId(resultSet.getInt("receiver_id"));
                notification.setCreationDate(resultSet.getString("creation_date"));
                notification.setIsRead(resultSet.getInt("is_read"));
                notification.setType(resultSet.getString("type"));
                notification.setContent(resultSet.getString("content"));
                notification.setSeenDate(resultSet.getString("seen_date"));
                notification.setPostId(resultSet.getInt("post_id"));
                result.add(notification);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return result;
    }
}
