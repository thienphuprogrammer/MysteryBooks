package com.example.mysterybook.daos.friend;

import com.example.mysterybook.dto.friend.RenderFriendDto;
import com.example.mysterybook.models.Friend;
import com.example.mysterybook.models.FriendRequest;
import com.example.mysterybook.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendDao implements IFriendDao {
    private static Connection connection = null;

    public FriendDao() throws Exception {
        connection = DatabaseUtil.getInstance().getConnection();
    }
    @Override
    public List<Friend> getFriends() throws SQLException {
        List<Friend> listFriend = new ArrayList<>();
        try {
            String query = """
                    SELECT * FROM friends
              """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Friend friend = new Friend();
                friend.setId(resultSet.getInt("id"));
                friend.setUserId(resultSet.getInt("user_id"));
                friend.setFriendId(resultSet.getInt("friend_id"));
                listFriend.add(friend);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return listFriend;
    }

    @Override
    public List<Friend> getFriendsByUserId(int i) {
        List<Friend> listFriend = new ArrayList<>();
        try {
            String query = """
                    SELECT * FROM friends WHERE user_id = ?
              """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, i);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Friend friend = new Friend();
                friend.setId(resultSet.getInt("id"));
                friend.setUserId(resultSet.getInt("user_id"));
                friend.setFriendId(resultSet.getInt("friend_id"));
                listFriend.add(friend);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return listFriend;
    }

    @Override
    public boolean unfriend(Friend friend) {
        boolean result = false;
        try {
            String query = """
                    DELETE FROM friends WHERE (user_id = ? AND friend_id = ?) OR (user_id = ? AND friend_id = ?)
              """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, friend.getUserId());
            preparedStatement.setInt(2, friend.getFriendId());
            preparedStatement.setInt(3, friend.getFriendId());
            preparedStatement.setInt(4, friend.getUserId());
            result = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }
}
