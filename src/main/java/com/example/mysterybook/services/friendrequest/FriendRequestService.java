package com.example.mysterybook.services.friendrequest;

import com.example.mysterybook.daos.DaoFactory;
import com.example.mysterybook.daos.friend.IFriendDao;
import com.example.mysterybook.daos.friendrequests.IFriendRequestDao;
import com.example.mysterybook.dto.friend.RenderFriendRequestDto;
import com.example.mysterybook.dto.friend.RequestAddFriendDto;
import com.example.mysterybook.models.Friend;
import com.example.mysterybook.models.FriendRequest;
import com.example.mysterybook.models.User;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestService implements IFriendRequestService {
    private static FriendRequestService instance;
    IFriendRequestDao friendRequestDao = null;

    private FriendRequestService() throws Exception {
        friendRequestDao = DaoFactory.getInstance().getFriendRequestDao();
    }

    public static FriendRequestService getInstance() throws Exception {
        if (instance == null) {
            instance = new FriendRequestService();
        }

        return instance;
    }

    @Override
    public boolean requestAddFriend(RequestAddFriendDto dto) throws Exception {
        boolean result = false;
        if (friendRequestDao != null) {
            FriendRequest friend = new FriendRequest();
            friend.loadFromDto(dto);
            // check if friend request already exists
            FriendRequest existingFriendRequest = friendRequestDao.getFriendRequest(friend);
            if (existingFriendRequest == null) {
                result = friendRequestDao.requestAddFriend(friend);
            }
        }
        return result;
    }

    @Override
    public boolean cancelAddFriend(RequestAddFriendDto dto) throws Exception {
        boolean result = false;
        if (friendRequestDao != null) {
            FriendRequest friend = new FriendRequest();
            friend.loadFromDto(dto);
            FriendRequest existingFriendRequest = friendRequestDao.getFriendRequest(friend);
            if (existingFriendRequest != null) {
                result = friendRequestDao.cancelAddFriend(friend);
            }
        }
        return result;
    }

    @Override
    public boolean declineFriendRequest(RequestAddFriendDto dto) throws Exception {
        boolean result = false;
        if (friendRequestDao != null) {
            FriendRequest friend = new FriendRequest();
            friend.loadFromDto(dto);
            FriendRequest existingFriendRequest = friendRequestDao.getFriendRequest(friend);
            if (existingFriendRequest != null) {
                result = friendRequestDao.declineFriendRequest(friend);
            }
        }
        return result;
    }

    @Override
    public List<RenderFriendRequestDto> getFriendRequestByUserId(int userId) throws Exception {
        List<RenderFriendRequestDto> listFriendRequest = new ArrayList<>();
        if (friendRequestDao != null) {
            List<FriendRequest> friends = friendRequestDao.getFriendRequestByUserId(userId);
            for (FriendRequest friend : friends) {
                RenderFriendRequestDto renderFriendRequestDto = new RenderFriendRequestDto();
                User user = DaoFactory.getInstance().getUserDao().getUserById(friend.getSenderId());
                renderFriendRequestDto.loadFromModel(friend, user);
                listFriendRequest.add(renderFriendRequestDto);
            }
        }
        return listFriendRequest;
    }

    @Override
    public boolean acceptFriendRequest(RequestAddFriendDto dto) throws Exception {
        boolean result = false;
        if (friendRequestDao != null) {
            FriendRequest friendRequest = new FriendRequest();
            friendRequest.loadFromDto(dto);
            friendRequest.setAccepted(true);
            result = friendRequestDao.acceptFriendRequest(friendRequest);
        }
        return result;
    }
}
