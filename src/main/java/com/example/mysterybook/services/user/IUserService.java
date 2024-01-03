package com.example.mysterybook.services.user;

import com.example.mysterybook.dto.user.*;
import com.example.mysterybook.models.User;

import java.util.List;

public interface IUserService {
    public boolean registerUser(RegisterUserDto userDto) throws Exception;

    User loginUser(LoginUserDto dto) throws Exception;

    List<RenderUserDto> getUsersExceptCurrentAndFriends(int userId) throws Exception;

    RenderUserDto getUserById(int i) throws Exception;

    boolean updateInfoUser(UpdateInfoUserDto userDto);

    boolean updateUserFullName(UpdateFullNameUserDto userDto);

    boolean updateUserPassword(UpdatePasswordUserDto userDto) throws Exception;

    boolean updateUserAvatar(UpdateAvatarUserDto userDto);
}
