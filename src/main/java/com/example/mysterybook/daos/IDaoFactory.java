package com.example.mysterybook.daos;

import com.example.mysterybook.daos.friend.IFriendDao;
import com.example.mysterybook.daos.friendrequests.IFriendRequestDao;
import com.example.mysterybook.daos.message.IMessageDao;
import com.example.mysterybook.daos.notification.INotificationDao;
import com.example.mysterybook.daos.comment.ICommentDao;
import com.example.mysterybook.daos.emotion.IEmotionDao;
import com.example.mysterybook.daos.image.IImageDao;
import com.example.mysterybook.daos.post.IPostDao;
import com.example.mysterybook.daos.user.IUserDao;

public interface IDaoFactory {
    IUserDao getUserDao() throws Exception;

    IPostDao getPostsDao() throws Exception;

    ICommentDao getCommentDao() throws Exception;

    IImageDao getImageDao() throws Exception;

    IEmotionDao getEmotionDao() throws Exception;
    IFriendDao getFriendDao() throws Exception;
    IFriendRequestDao getFriendRequestDao() throws Exception;
    IMessageDao getMessageDao() throws Exception;

    INotificationDao getNotificationDao() throws Exception;
}
