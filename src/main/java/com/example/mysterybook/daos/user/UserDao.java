package com.example.mysterybook.daos.user;

import com.example.mysterybook.models.User;
import com.example.mysterybook.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {
    private static Connection connection;

    public UserDao() throws Exception {
        connection = DatabaseUtil.getInstance().getConnection();
    }

    @Override
    public boolean createUser(User user) throws Exception {
        boolean result = false;
        try {
            String sql = "INSERT INTO users (username, email, password, full_name, phone_number) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFullName());
            statement.setString(5, user.getPhoneNumber());
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return result;
    }

    @Override
    public User findUserByUsernameAndPassword(User user) throws Exception {
        User result = null;
        try {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeQuery();
            if (statement.getResultSet().next()) {
                result = new User();
                result.setId(statement.getResultSet().getInt("id"));
                result.setUsername(statement.getResultSet().getString("username"));
                result.setEmail(statement.getResultSet().getString("email"));
                result.setPassword(statement.getResultSet().getString("password"));
                result.setPhoneNumber(statement.getResultSet().getString("phone_number"));
                result.setBio(statement.getResultSet().getString("bio"));
                result.setInterests(statement.getResultSet().getString("interests"));
                result.setProfilePicture(statement.getResultSet().getString("profile_picture"));
                result.setCreationDate(statement.getResultSet().getString("creation_date"));
                result.setLastLoginDate(statement.getResultSet().getString("last_login_date"));
                result.setFullName(statement.getResultSet().getString("full_name"));
                result.setAddress(statement.getResultSet().getString("address"));
                result.setDateOfBirth(statement.getResultSet().getString("date_of_birth"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return result;
    }

    @Override
    public User getUserById(int userId) throws Exception {
        User result = new User();
        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.executeQuery();
            if (statement.getResultSet().next()) {
                User user = new User();
                user.setId(statement.getResultSet().getInt("id"));
                user.setUsername(statement.getResultSet().getString("username"));
                user.setFullName(statement.getResultSet().getString("full_name"));
                user.setEmail(statement.getResultSet().getString("email"));
                user.setPassword(statement.getResultSet().getString("password"));
                user.setPhoneNumber(statement.getResultSet().getString("phone_number"));
                user.setBio(statement.getResultSet().getString("bio"));
                user.setInterests(statement.getResultSet().getString("interests"));
                user.setProfilePicture(statement.getResultSet().getString("profile_picture"));
                user.setCreationDate(statement.getResultSet().getString("creation_date"));
                user.setLastLoginDate(statement.getResultSet().getString("last_login_date"));
                user.setAddress(statement.getResultSet().getString("address"));
                user.setDateOfBirth(statement.getResultSet().getString("date_of_birth"));
                result = user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return result;
    }

    @Override
    public List<User> getUsersExceptCurrentAndFriends(int userId) {
        List<User> result = new ArrayList<>();
        // Select all users except current user and friends in friend_requests table and users table and friends table
        String query = """
                SELECT * FROM users
                WHERE id NOT IN (
                    SELECT sender_id FROM friend_requests
                    WHERE receiver_id = ?
                    UNION
                    SELECT receiver_id FROM friend_requests
                    WHERE sender_id = ? AND accepted = true
                    UNION
                    SELECT user_id FROM friends
                    WHERE friend_id = ?
                    UNION
                    SELECT friend_id FROM friends
                    WHERE user_id = ?
                ) AND id != ?
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, userId);
            preparedStatement.setInt(4, userId);
            preparedStatement.setInt(5, userId);
            preparedStatement.executeQuery();
            while (preparedStatement.getResultSet().next()) {
                User user = new User();
                user.setId(preparedStatement.getResultSet().getInt("id"));
                user.setUsername(preparedStatement.getResultSet().getString("username"));
                user.setFullName(preparedStatement.getResultSet().getString("full_name"));
                user.setEmail(preparedStatement.getResultSet().getString("email"));
                user.setPassword(preparedStatement.getResultSet().getString("password"));
                user.setPhoneNumber(preparedStatement.getResultSet().getString("phone_number"));
                user.setBio(preparedStatement.getResultSet().getString("bio"));
                user.setInterests(preparedStatement.getResultSet().getString("interests"));
                user.setProfilePicture(preparedStatement.getResultSet().getString("profile_picture"));
                user.setCreationDate(preparedStatement.getResultSet().getString("creation_date"));
                user.setLastLoginDate(preparedStatement.getResultSet().getString("last_login_date"));
                result.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean updateInfoUser(User user) {
        boolean result = false;
        try {
            String sql = """
                    UPDATE users
                    SET phone_number = ?, date_of_birth = ?, address = ?, bio = ?, interests = ?
                    WHERE id = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getPhoneNumber());
            statement.setString(2, user.getDateOfBirth());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getBio());
            statement.setString(5, user.getInterests());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean updateUserFullName(User user) {
        boolean result = false;
        try {
            String sql = """
                    UPDATE users
                    SET full_name = ?
                    WHERE id = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getFullName());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean updateUserPassword(User user) {
        boolean result = false;
        try {
            String sql = """
                    UPDATE users
                    SET password = ?
                    WHERE id = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getPassword());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean updateUserAvatar(User user) {
        boolean result = false;
        try {
            String sql = """
                    UPDATE users
                    SET profile_picture = ?
                    WHERE id = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getProfilePicture());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }
}
