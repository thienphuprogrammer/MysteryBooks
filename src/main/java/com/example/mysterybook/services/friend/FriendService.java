package com.example.mysterybook.services.friend;

import com.example.mysterybook.daos.DaoFactory;
import com.example.mysterybook.daos.friend.IFriendDao;
import com.example.mysterybook.dto.friend.RenderFriendDto;
import com.example.mysterybook.dto.friend.UnfriendDto;
import com.example.mysterybook.dto.user.RenderUserDto;
import com.example.mysterybook.models.Friend;
import com.example.mysterybook.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendService implements IFriendService {
    private static FriendService instance;
    IFriendDao friendDao = null;

    private FriendService() throws Exception {
        friendDao = DaoFactory.getInstance().getFriendDao();
    }

    public static FriendService getInstance() throws Exception {
        if (instance == null) {
            instance = new FriendService();
        }

        return instance;
    }

    @Override
    public List<RenderFriendDto> getFriendsByUserId(int i) throws Exception {
        List<RenderFriendDto> listFriend = new ArrayList<>();
        if (friendDao != null) {
            List<Friend> friends = friendDao.getFriendsByUserId(i);
            for (Friend friend : friends) {
                User user = DaoFactory.getInstance().getUserDao().getUserById(friend.getFriendId());
                RenderFriendDto renderFriendDto = new RenderFriendDto();
                renderFriendDto.loadFromModel(friend, user);
                listFriend.add(renderFriendDto);
            }
        }
        return listFriend;
    }

    @Override
    public boolean unfriend(UnfriendDto dto) {
        boolean result = false;
        if (friendDao != null) {
            Friend friend = new Friend();
            friend.setUserId(dto.getUserId());
            friend.setFriendId(dto.getFriendId());
            result = friendDao.unfriend(friend);
        }
        return result;
    }
}
