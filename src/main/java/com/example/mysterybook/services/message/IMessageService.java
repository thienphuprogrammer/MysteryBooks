package com.example.mysterybook.services.message;

import com.example.mysterybook.dto.message.MessageFriendsDto;
import com.example.mysterybook.dto.message.RenderMessageDto;
import com.example.mysterybook.dto.message.UploadMessageDto;

import java.util.List;

public interface IMessageService {
    List<MessageFriendsDto> getFriendsByUserId(int i);

    List<RenderMessageDto> getALlMessages(RenderMessageDto dto) throws Exception;

    boolean createMessage(UploadMessageDto dto);

    boolean deleteMessage(int i, int i1);
}
