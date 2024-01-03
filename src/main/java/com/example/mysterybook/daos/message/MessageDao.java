package com.example.mysterybook.daos.message;

import com.example.mysterybook.daos.friend.IFriendDao;
import com.example.mysterybook.dto.message.MessageFriendsDto;
import com.example.mysterybook.models.Friend;
import com.example.mysterybook.models.Messages;
import com.example.mysterybook.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageDao implements IMessageDao {
    private static Connection connection = null;

    public MessageDao() throws Exception {
        connection = DatabaseUtil.getInstance().getConnection();
    }

    @Override
    public List<Messages> getAllMessages(Messages messages) throws Exception {
        List<Messages> messagesList = new ArrayList<>();
        String sql = """
                SELECT * FROM messages
                WHERE (sender_id = ? AND receiver_id = ?)
                OR (sender_id = ? AND receiver_id = ?)
                ORDER BY creation_date ASC
        """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, messages.getSenderId());
            preparedStatement.setInt(2, messages.getReceiverId());
            preparedStatement.setInt(3, messages.getReceiverId());
            preparedStatement.setInt(4, messages.getSenderId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Messages message = new Messages();
                message.setId(resultSet.getInt("id"));
                message.setSenderId(resultSet.getInt("sender_id"));
                message.setReceiverId(resultSet.getInt("receiver_id"));
                message.setContent(resultSet.getString("content"));
                message.setSentDate(String.valueOf(resultSet.getDate("creation_date")));
                messagesList.add(message);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return messagesList;
    }
    @Override
    public Messages getLatestMessagesByUserIdAndFriendId(int userId, int friendId) {
        Messages result = new Messages();
        try {
            String query = """
                    SELECT * FROM messages
                    WHERE (sender_id = ? AND receiver_id = ?)
                    OR (sender_id = ? AND receiver_id = ?)
                    ORDER BY creation_date DESC
                    LIMIT 1
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, friendId);
            preparedStatement.setInt(3, friendId);
            preparedStatement.setInt(4, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Messages message = new Messages();
                message.setId(resultSet.getInt("id"));
                message.setSenderId(resultSet.getInt("sender_id"));
                message.setReceiverId(resultSet.getInt("receiver_id"));
                message.setContent(resultSet.getString("content"));
                message.setSentDate(resultSet.getString("creation_date"));
                result = message;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean createMessage(Messages messages) throws Exception {
        boolean result = false;
        String sql = """
                INSERT INTO messages (sender_id, receiver_id, content, creation_date)
                VALUES (?, ?, ?, ?)
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, messages.getSenderId());
            preparedStatement.setInt(2, messages.getReceiverId());
            preparedStatement.setString(3, messages.getContent());
            LocalDateTime now = LocalDateTime.now();
            preparedStatement.setString(4, now.toString());
            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean deleteMessage(int id, int userId) {
        boolean result = false;
        String sql = """
                DELETE FROM messages
                WHERE id = ? AND (sender_id = ?)
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
