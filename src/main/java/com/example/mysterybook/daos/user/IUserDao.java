package com.example.mysterybook.daos.user;

import com.example.mysterybook.models.User;

import java.util.List;

public interface IUserDao {
    boolean createUser(User user) throws Exception;

    User findUserByUsernameAndPassword(User user) throws Exception;

    User getUserById(int userId) throws Exception;

    List<User> getUsersExceptCurrentAndFriends(int userId);

    boolean updateInfoUser(User user);

    boolean updateUserFullName(User user);

    boolean updateUserPassword(User user);

    boolean updateUserAvatar(User user);
}
