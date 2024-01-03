package com.example.mysterybook.daos.message;

import com.example.mysterybook.dto.message.MessageFriendsDto;
import com.example.mysterybook.models.Friend;
import com.example.mysterybook.models.Messages;

import java.sql.SQLException;
import java.util.List;

public interface IMessageDao {
    List<Messages> getAllMessages(Messages messages) throws Exception;

    Messages getLatestMessagesByUserIdAndFriendId(int userId, int friendId);

    boolean createMessage(Messages messages) throws Exception;

    boolean deleteMessage(int id, int userId);
}
