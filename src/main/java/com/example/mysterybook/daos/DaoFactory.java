package com.example.mysterybook.daos;

import com.example.mysterybook.daos.friend.FriendDao;
import com.example.mysterybook.daos.friend.IFriendDao;
import com.example.mysterybook.daos.friendrequests.FriendRequestDao;
import com.example.mysterybook.daos.friendrequests.IFriendRequestDao;
import com.example.mysterybook.daos.message.IMessageDao;
import com.example.mysterybook.daos.message.MessageDao;
import com.example.mysterybook.daos.notification.INotificationDao;
import com.example.mysterybook.daos.notification.NotificationDao;
import com.example.mysterybook.daos.comment.CommentDao;
import com.example.mysterybook.daos.comment.ICommentDao;
import com.example.mysterybook.daos.emotion.EmotionDao;
import com.example.mysterybook.daos.emotion.IEmotionDao;
import com.example.mysterybook.daos.image.IImageDao;
import com.example.mysterybook.daos.image.ImageDao;
import com.example.mysterybook.daos.post.IPostDao;
import com.example.mysterybook.daos.post.PostDao;
import com.example.mysterybook.daos.user.IUserDao;
import com.example.mysterybook.daos.user.UserDao;

public class DaoFactory implements IDaoFactory {
    private static DaoFactory instance;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }

        return instance;
    }

    @Override
    public IUserDao getUserDao() throws Exception {
        return new UserDao();
    }

    @Override
    public IPostDao getPostsDao() throws Exception {
        return new PostDao();
    }

    @Override
    public ICommentDao getCommentDao() throws Exception {
        return new CommentDao();
    }

    @Override
    public IImageDao getImageDao() throws Exception {
        return new ImageDao();
    }

    @Override
    public IEmotionDao getEmotionDao() throws Exception {
        return new EmotionDao();
    }

    @Override
    public IFriendDao getFriendDao() throws Exception {
        return new FriendDao();
    }

    @Override
    public IFriendRequestDao getFriendRequestDao() throws Exception {
        return new FriendRequestDao();
    }

    @Override
    public IMessageDao getMessageDao() throws Exception {
        return new MessageDao();
    }

    @Override
    public INotificationDao getNotificationDao() throws Exception {
        return new NotificationDao();
    }
}
