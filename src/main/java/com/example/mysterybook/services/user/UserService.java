package com.example.mysterybook.services.user;

import com.example.mysterybook.daos.DaoFactory;
import com.example.mysterybook.daos.user.IUserDao;
import com.example.mysterybook.dto.user.*;
import com.example.mysterybook.models.FriendRequest;
import com.example.mysterybook.models.User;
import com.example.mysterybook.utils.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    private static UserService instance;
    IUserDao userDao = null;

//    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private UserService() throws Exception {
        try {
            userDao = DaoFactory.getInstance().getUserDao();
        } catch (Exception e) {
            throw e;
        }
    }

    public static UserService getInstance() throws Exception {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public boolean registerUser(RegisterUserDto userDto) throws Exception {
        boolean result = false;
        if (userDao != null) {
            User user = new User();
            user.loadFromDto(userDto);
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
            result = userDao.createUser(user);
        }
        return result;
    }

    @Override
    public User loginUser(LoginUserDto dto) throws Exception {
        User result = null;
        if (userDao != null) {
            User user = new User();
            user.loadFromDto(dto);
            user.setPassword(user.getPassword());
            User newUser = userDao.findUserByUsernameAndPassword(user);
            if (newUser != null) {
                result = newUser;
            }
        }
        return result;
    }

    @Override
    public List<RenderUserDto> getUsersExceptCurrentAndFriends(int userId) throws Exception {
        List<RenderUserDto> result = new ArrayList<>();
        if (userDao != null) {
            List<User> users = userDao.getUsersExceptCurrentAndFriends(userId);
            if (users != null) {
                for (User user : users) {
                    RenderUserDto dto = new RenderUserDto();
                    dto.loadFromModel(user);

                    FriendRequest friendRequest = new FriendRequest();
                    friendRequest.setSenderId(userId);
                    friendRequest.setReceiverId(user.getId());
                    FriendRequest existingFriendRequest = DaoFactory.getInstance().getFriendRequestDao().getFriendRequest(friendRequest);
                    if (existingFriendRequest != null) {
                        dto.setStatusRequestFriend(1);
                    } else {
                        dto.setStatusRequestFriend(0);
                    }
                    result.add(dto);
                }
            }
        }
        return result;
    }

    @Override
    public RenderUserDto getUserById(int i) throws Exception {
        RenderUserDto result = new RenderUserDto();
        if (userDao != null) {
            User user = userDao.getUserById(i);
            if (user != null) {
                result.loadFromModel(user);
            }
        }
        return result;
    }

    @Override
    public boolean updateInfoUser(UpdateInfoUserDto userDto) {
        boolean result = false;
        if (userDao != null) {
            User user = new User();
            user.loadFromDto(userDto);
            result = userDao.updateInfoUser(user);
        }
        return result;
    }

    @Override
    public boolean updateUserFullName(UpdateFullNameUserDto userDto) {
        boolean result = false;
        if (userDao != null) {
            User user = new User();
            user.loadFromDto(userDto);
            result = userDao.updateUserFullName(user);
        }
        return result;
    }

    @Override
    public boolean updateUserPassword(UpdatePasswordUserDto userDto) throws Exception {
        boolean result = false;

        if (!userDto.getNewPassword().equals(userDto.getConfirmPassword())) {
            throw new Exception("Confirm password is not match");
        }

        User user = userDao.getUserById(userDto.getId());
        if (user == null) {
            throw new Exception("User not found");
        }

        if (!user.getPassword().equals(userDto.getOldPassword())) {
            throw new Exception("Old password is not correct");
        }

        if (!ValidationUtil.isPasswordValid(userDto.getNewPassword())) {
            throw new Exception("New password is invalid");
        }

        if (userDao != null) {
            User newUser = new User();
            newUser.loadFromDto(userDto);
            result = userDao.updateUserPassword(newUser);
        }
        return result;
    }

    @Override
    public boolean updateUserAvatar(UpdateAvatarUserDto userDto) {
        boolean result = false;
        if (userDao != null) {
            User user = new User();
            user.loadFromDto(userDto);
            result = userDao.updateUserAvatar(user);
        }
        return result;
    }
}
