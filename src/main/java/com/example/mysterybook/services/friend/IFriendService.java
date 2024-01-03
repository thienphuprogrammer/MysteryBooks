package com.example.mysterybook.services.friend;

import com.example.mysterybook.dto.friend.RenderFriendDto;
import com.example.mysterybook.dto.friend.UnfriendDto;

import java.sql.SQLException;
import java.util.List;

public interface IFriendService {
    List<RenderFriendDto> getFriendsByUserId(int i) throws Exception;

    boolean unfriend(UnfriendDto dto);
}
