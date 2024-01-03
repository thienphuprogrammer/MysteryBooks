package com.example.mysterybook.daos.comment;

import com.example.mysterybook.dto.comment.UploadCommentDto;
import com.example.mysterybook.models.Comments;
import com.example.mysterybook.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDao implements ICommentDao {
    @Override
    public boolean uploadComment(UploadCommentDto dto) throws Exception {
        boolean result = false;
        try {
            String query = "INSERT INTO comments (post_id, user_id, content, creation_date) VALUES (?, ?, ?, ?)";
            Connection connection = DatabaseUtil.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, dto.getPostId());
            statement.setInt(2, dto.getUserId());
            statement.setString(3, dto.getContent());
            LocalDateTime now = LocalDateTime.now();
            statement.setString(4, now.toString());
            statement.executeUpdate();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Comments> getCommentsByPostId(int id) throws Exception {
        List<Comments> comments = new ArrayList<>();
        try {
            String query = "SELECT * FROM comments WHERE post_id = ?";
            Connection connection = DatabaseUtil.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Comments comment = new Comments();
                comment.setId(resultSet.getInt("id"));
                comment.setPostId(resultSet.getInt("post_id"));
                comment.setUserId(resultSet.getInt("user_id"));
                comment.setContent(resultSet.getString("content"));
                comment.setCreationDate(resultSet.getString("creation_date"));
                comments.add(comment);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return comments;
    }
}
