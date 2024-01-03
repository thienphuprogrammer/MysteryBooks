package com.example.mysterybook.daos.friendrequests;

import com.example.mysterybook.models.Friend;
import com.example.mysterybook.models.FriendRequest;
import com.example.mysterybook.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestDao implements IFriendRequestDao {
    private static Connection connection = null;

    public FriendRequestDao() throws Exception {
        connection = DatabaseUtil.getInstance().getConnection();
    }

    @Override
    public boolean requestAddFriend(FriendRequest friend) throws Exception {
        boolean result = false;
        try {
            String query = """
                    INSERT INTO friend_requests (sender_id, receiver_id, sent_date, accepted)
                    VALUES (?, ?, ?, ?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, friend.getSenderId());
            preparedStatement.setInt(2, friend.getReceiverId());
            LocalDateTime now = LocalDateTime.now();
            preparedStatement.setString(3, now.toString());
            preparedStatement.setBoolean(4, friend.isAccepted());

            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return result;
    }

    @Override
    public FriendRequest getFriendRequest(FriendRequest friend) throws Exception {
        FriendRequest result = null;
        try {
            String query = """
                    SELECT * FROM friend_requests
                    WHERE sender_id = ? AND receiver_id = ? AND accepted = false
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, friend.getSenderId());
            preparedStatement.setInt(2, friend.getReceiverId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = new FriendRequest();
                result.setId(resultSet.getInt("id"));
                result.setSenderId(resultSet.getInt("sender_id"));
                result.setReceiverId(resultSet.getInt("receiver_id"));
                result.setSentDate(resultSet.getString("sent_date"));
                result.setAccepted(resultSet.getBoolean("accepted"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean cancelAddFriend(FriendRequest friend) {
        boolean result = false;
        String query = """
                DELETE FROM friend_requests
                WHERE sender_id = ? AND receiver_id = ? AND accepted = false
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, friend.getSenderId());
            preparedStatement.setInt(2, friend.getReceiverId());
            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean acceptFriendRequest(FriendRequest friend) {
        boolean result = false;
        String query = """
                UPDATE friend_requests
                SET accepted = ?, accepted_date = ?
                WHERE sender_id = ? AND receiver_id = ? AND accepted = false AND id = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, friend.isAccepted());
            LocalDateTime now = LocalDateTime.now();
            preparedStatement.setString(2, now.toString());
            preparedStatement.setInt(3, friend.getSenderId());
            preparedStatement.setInt(4, friend.getReceiverId());
            preparedStatement.setInt(5, friend.getId());
            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean declineFriendRequest(FriendRequest friend) {
        boolean result = false;
        String query = """
                DELETE FROM friend_requests
                WHERE sender_id = ? AND receiver_id = ? AND accepted = false AND id = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, friend.getSenderId());
            preparedStatement.setInt(2, friend.getReceiverId());
            preparedStatement.setInt(3, friend.getId());
            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public List<FriendRequest> getFriendRequestByUserId(int userId) {
        List<FriendRequest> listFriendRequest = new ArrayList<>();
        try {
            String query = """
                    SELECT * FROM friend_requests
                    WHERE receiver_id = ? AND accepted = false
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                FriendRequest friendRequest = new FriendRequest();
                friendRequest.setId(resultSet.getInt("id"));
                friendRequest.setSenderId(resultSet.getInt("sender_id"));
                friendRequest.setReceiverId(resultSet.getInt("receiver_id"));
                friendRequest.setSentDate(resultSet.getString("sent_date"));
                friendRequest.setAccepted(resultSet.getBoolean("accepted"));
                listFriendRequest.add(friendRequest);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return listFriendRequest;
    }
}
