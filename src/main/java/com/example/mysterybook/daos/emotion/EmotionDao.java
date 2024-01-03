package com.example.mysterybook.daos.emotion;

import com.example.mysterybook.models.Emotion;
import com.example.mysterybook.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class EmotionDao implements IEmotionDao {
    private static Connection connection = null;

    public EmotionDao() throws Exception {
        connection = DatabaseUtil.getInstance().getConnection();
    }

    @Override
    public long getNumberOfEmotionsByPostId(int id) {
        long result = 0;
        String query = """
                SELECT COUNT(*) AS total FROM emotions
                WHERE post_id = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getLong("total");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean createEmotion(Emotion emotion) {
        boolean result = false;
        String query = """
                INSERT INTO emotions (post_id, user_id, emotion, creation_date)
                VALUES (?, ?, ?, ?)
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, emotion.getPostId());
            preparedStatement.setInt(2, emotion.getUserId());
            preparedStatement.setString(3, emotion.getType());
            LocalDateTime now = LocalDateTime.now();
            preparedStatement.setString(4, now.toString());
            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public Emotion getEmotionByPostIdAndUserId(int postId, int userId) {
        Emotion result = null;
        String query = """
                SELECT * FROM emotions
                WHERE post_id = ? AND user_id = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = new Emotion();
                result.setId(resultSet.getInt("id"));
                result.setPostId(resultSet.getInt("post_id"));
                result.setUserId(resultSet.getInt("user_id"));
                result.setType(resultSet.getString("emotion"));
                result.setCreationDate(resultSet.getString("creation_date"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean deleteEmotion(Emotion emotion) {
        boolean result = false;
        String query = """
                DELETE FROM emotions
                WHERE id = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, emotion.getId());
            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean updateEmotion(Emotion emotion) {
        boolean result = false;
        String query = """
                UPDATE emotions
                SET emotion = ?
                WHERE id = ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, emotion.getType());
            preparedStatement.setInt(2, emotion.getId());
            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }
}
