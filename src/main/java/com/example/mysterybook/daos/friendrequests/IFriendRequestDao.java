package com.example.mysterybook.daos.friendrequests;

import com.example.mysterybook.models.Friend;
import com.example.mysterybook.models.FriendRequest;

import java.util.List;

public interface IFriendRequestDao {
    boolean requestAddFriend(FriendRequest friend) throws Exception;

    FriendRequest getFriendRequest(FriendRequest friend) throws Exception;

    boolean cancelAddFriend(FriendRequest friend);
    public List<FriendRequest> getFriendRequestByUserId(int userId) throws Exception;
    boolean acceptFriendRequest(FriendRequest friend);

    boolean declineFriendRequest(FriendRequest friend);
}
