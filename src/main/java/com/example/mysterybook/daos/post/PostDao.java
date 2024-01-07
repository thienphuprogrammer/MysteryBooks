package com.example.mysterybook.daos.post;

import com.example.mysterybook.models.Posts;
import com.example.mysterybook.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDao implements IPostDao {
    private static Connection connection = null;

    public PostDao() throws SQLException {
        connection = DatabaseUtil.getInstance().getConnection();
    }

    @Override
    public ArrayList<Posts> getPosts() throws SQLException {
        ArrayList<Posts> result = new ArrayList<>();
        String query = """
                SELECT * FROM posts
                ORDER BY creation_date DESC
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Posts post = new Posts();
            post.setId(resultSet.getInt("id"));
            post.setUserId(resultSet.getInt("user_id"));
            post.setGroupId(resultSet.getInt("group_id"));
            post.setContent(resultSet.getString("content"));
            post.setVisibility(resultSet.getString("visibility"));
            post.setTitle(resultSet.getString("title"));
            post.setCreationDate(resultSet.getString("creation_date"));
        }
        return result;
    }

    @Override
    public List<Posts> getPostsByUserId(int userId) throws SQLException {
        ArrayList<Posts> result = new ArrayList<>();
        String query = """
                SELECT * FROM posts
                WHERE user_id = ?
                ORDER BY creation_date DESC
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Posts post = new Posts();
            post.setId(resultSet.getInt("id"));
            post.setUserId(resultSet.getInt("user_id"));
            post.setGroupId(resultSet.getInt("group_id"));
            post.setContent(resultSet.getString("content"));
            post.setVisibility(resultSet.getString("visibility"));
            post.setTitle(resultSet.getString("title"));
            post.setCreationDate(resultSet.getString("creation_date"));
            result.add(post);
        }
        return result;
    }

    @Override
    public int createPost(Posts dto) throws SQLException {
        int id = 0;
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO posts (user_id, group_id, creation_date, content, visibility, title) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, dto.getUserId());
            if (dto.getGroupId() == 0) {
                preparedStatement.setNull(2, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setInt(2, dto.getGroupId());
            }
            // get time and date
            LocalDateTime now = LocalDateTime.now();
            preparedStatement.setString(3, now.toString());
            preparedStatement.setString(4, dto.getContent());
            preparedStatement.setString(5, dto.getVisibility());
            preparedStatement.setString(6, dto.getTitle());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return id == 0 ? -1 : id;
    }

    @Override
    public Posts getPostById(int i) {
        Posts result = new Posts();
        String query = """
                SELECT * FROM posts
                WHERE id = ?
                """;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, i);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result.setId(resultSet.getInt("id"));
                result.setUserId(resultSet.getInt("user_id"));
                result.setGroupId(resultSet.getInt("group_id"));
                result.setContent(resultSet.getString("content"));
                result.setVisibility(resultSet.getString("visibility"));
                result.setTitle(resultSet.getString("title"));
                result.setCreationDate(resultSet.getString("creation_date"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean updatePost(Posts post) {
        boolean result = false;
        String query = """
                UPDATE posts
                SET title = ?, content = ?, visibility = ?
                WHERE id = ? and user_id = ?
                """;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, post.getVisibility());
            preparedStatement.setInt(4, post.getId());
            preparedStatement.setInt(5, post.getUserId());
            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean deletePost(Posts posts) {
        boolean result = false;
        String query = """
                DELETE FROM posts
                WHERE id = ? AND user_id = ?
                """;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, posts.getId());
            preparedStatement.setInt(2, posts.getUserId());
            preparedStatement.executeUpdate();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Posts> getAllPosts(int userId) {
        // get all posts, include posts of user and posts of friends, and posts is public
        List<Posts> result = new ArrayList<>();
        String query = """
                SELECT * FROM posts
                WHERE visibility = 'public'
                OR group_id IN (SELECT group_id FROM user_groups WHERE user_id = ?)
                OR id IN (SELECT id FROM posts
                            WHERE visibility = 'friends'
                            AND user_id IN (SELECT friend_id FROM friends WHERE user_id = ?))
                OR user_id = ?
                ORDER BY
                    group_id DESC,
                    user_id IN (SELECT friend_id FROM friends WHERE user_id = ?) DESC,
                    user_id = ? DESC,
                    creation_date DESC;
                """;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, userId);
            preparedStatement.setInt(4, userId);
            preparedStatement.setInt(5, userId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Posts post = new Posts();
                post.setId(resultSet.getInt("id"));
                post.setUserId(resultSet.getInt("user_id"));
                post.setGroupId(resultSet.getInt("group_id"));
                post.setContent(resultSet.getString("content"));
                post.setVisibility(resultSet.getString("visibility"));
                post.setTitle(resultSet.getString("title"));
                post.setCreationDate(resultSet.getString("creation_date"));
                result.add(post);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Posts> getPostsForGuest(int friendId, int userId) {
        List<Posts> result = new ArrayList<>();
        String query = """
                SELECT * FROM posts
                WHERE (visibility = 'public' AND user_id = ?)
                OR (group_id IN (SELECT group_id FROM user_groups WHERE user_id = ?) AND user_id = ?)
                OR (id IN (SELECT id FROM posts
                            WHERE visibility = 'friends'
                            AND user_id IN (SELECT friend_id FROM friends WHERE user_id = ?)) AND user_id = ?)
                ORDER BY
                    group_id DESC,
                    user_id IN (SELECT friend_id FROM friends WHERE user_id = ?) DESC,
                    user_id = ? DESC,
                    creation_date DESC;
                """;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, friendId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, friendId);
            preparedStatement.setInt(4, userId);
            preparedStatement.setInt(5, friendId);
            preparedStatement.setInt(6, userId);
            preparedStatement.setInt(7, friendId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Posts post = new Posts();
                post.setId(resultSet.getInt("id"));
                post.setUserId(resultSet.getInt("user_id"));
                post.setGroupId(resultSet.getInt("group_id"));
                post.setContent(resultSet.getString("content"));
                post.setVisibility(resultSet.getString("visibility"));
                post.setTitle(resultSet.getString("title"));
                post.setCreationDate(resultSet.getString("creation_date"));
                result.add(post);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Posts> getAllPostsBySearch(int userId, String search) {
        String query = """
                SELECT * FROM posts
                WHERE (visibility = 'public' AND user_id = ? AND (title LIKE ? OR content LIKE ?))
                OR (id IN (SELECT id FROM posts
                            WHERE visibility = 'friends'
                            AND user_id IN (SELECT friend_id FROM friends WHERE user_id = ?)) AND user_id = ? AND (title LIKE ? OR content LIKE ?))
                ORDER BY
                    group_id DESC,
                    user_id IN (SELECT friend_id FROM friends WHERE user_id = ?) DESC,
                    user_id = ? DESC,
                    creation_date DESC;
                """;
        List<Posts> result = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, "%" + search + "%");
            preparedStatement.setString(3, "%" + search + "%");
            preparedStatement.setInt(4, userId);
            preparedStatement.setInt(5, userId);
            preparedStatement.setString(6, "%" + search + "%");
            preparedStatement.setString(7, "%" + search + "%");
            preparedStatement.setInt(8, userId);
            preparedStatement.setInt(9, userId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Posts post = new Posts();
                post.setId(resultSet.getInt("id"));
                post.setUserId(resultSet.getInt("user_id"));
                post.setGroupId(resultSet.getInt("group_id"));
                post.setContent(resultSet.getString("content"));
                post.setVisibility(resultSet.getString("visibility"));
                post.setTitle(resultSet.getString("title"));
                post.setCreationDate(resultSet.getString("creation_date"));
                result.add(post);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }
}
