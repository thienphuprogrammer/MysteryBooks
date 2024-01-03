package com.example.mysterybook.daos.friend;

import com.example.mysterybook.models.Friend;
import com.example.mysterybook.models.FriendRequest;

import java.sql.SQLException;
import java.util.List;

public interface IFriendDao {
    List<Friend> getFriends() throws SQLException;

    List<Friend> getFriendsByUserId(int i);

    boolean unfriend(Friend friend);
}
