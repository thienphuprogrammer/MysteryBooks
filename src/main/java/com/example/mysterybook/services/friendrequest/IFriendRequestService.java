package com.example.mysterybook.services.friendrequest;

import com.example.mysterybook.dto.friend.RenderFriendDto;
import com.example.mysterybook.dto.friend.RenderFriendRequestDto;
import com.example.mysterybook.dto.friend.RequestAddFriendDto;

import java.sql.SQLException;
import java.util.List;

public interface IFriendRequestService {
    List<RenderFriendRequestDto> getFriendRequestByUserId(int userId) throws Exception;

    boolean acceptFriendRequest(RequestAddFriendDto dto) throws Exception;

    boolean requestAddFriend(RequestAddFriendDto dto) throws Exception;

    boolean cancelAddFriend(RequestAddFriendDto dto) throws Exception;

    boolean declineFriendRequest(RequestAddFriendDto dto) throws Exception;
}
