package com.example.mysterybook.services.message;

import com.example.mysterybook.daos.DaoFactory;
import com.example.mysterybook.daos.message.IMessageDao;
import com.example.mysterybook.dto.message.MessageFriendsDto;
import com.example.mysterybook.dto.message.RenderMessageDto;
import com.example.mysterybook.dto.message.UploadMessageDto;
import com.example.mysterybook.models.Friend;
import com.example.mysterybook.models.Messages;
import com.example.mysterybook.models.User;

import java.util.ArrayList;
import java.util.List;

public class MessageService implements IMessageService {
    private static MessageService instance = null;
    IMessageDao messageDao = null;

    private MessageService() throws Exception {
        messageDao = DaoFactory.getInstance().getMessageDao();
    }

    public static MessageService getInstance() throws Exception {
        if (instance == null) {
            instance = new MessageService();
        }

        return instance;
    }

    @Override
    public List<RenderMessageDto> getALlMessages(RenderMessageDto dto) throws Exception {
        List<RenderMessageDto> result = new ArrayList<>();
        Messages messages = new Messages();
        messages.loadFromDto(dto);
        List<Messages> messagesList = messageDao.getAllMessages(messages);
        if (!messagesList.isEmpty()) {
            for (Messages message : messagesList) {
                RenderMessageDto renderMessageDto = new RenderMessageDto();
                User user = DaoFactory.getInstance().getUserDao().getUserById(message.getSenderId());
                renderMessageDto.loadFromModel(message, user);
                if (message.getSenderId() == dto.getUserId()) {
                    renderMessageDto.setIsFriend(1);
                } else {
                    renderMessageDto.setIsFriend(0);
                }
                result.add(renderMessageDto);
            }
        }
        return result;
    }

    @Override
    public boolean createMessage(UploadMessageDto dto) {
        boolean result = false;
        try {
            Messages messages = new Messages();
            messages.loadFromDto(dto);
            result = DaoFactory.getInstance().getMessageDao().createMessage(messages);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean deleteMessage(int id, int userId) {
        boolean result = false;
        try {
            result = DaoFactory.getInstance().getMessageDao().deleteMessage(id, userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<MessageFriendsDto> getFriendsByUserId(int i) {
        List<MessageFriendsDto> result = new ArrayList<>();
        try {
            List<Friend> friends = DaoFactory.getInstance().getFriendDao().getFriendsByUserId(i);
            if (friends.isEmpty()) {
                throw new Exception("No friends found");
            }
            for (Friend friend : friends) {
                MessageFriendsDto messageFriendsDto = new MessageFriendsDto();
                User friendUser = DaoFactory.getInstance().getUserDao().getUserById(friend.getFriendId());
                Messages friendsMessage = messageDao.getLatestMessagesByUserIdAndFriendId(friend.getUserId(), friend.getFriendId());
                messageFriendsDto.loadFromModel(friend, friendUser, friendsMessage);
                result.add(messageFriendsDto);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
