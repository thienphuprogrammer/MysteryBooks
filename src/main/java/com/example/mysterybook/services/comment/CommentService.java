package com.example.mysterybook.services.comment;

import com.example.mysterybook.daos.DaoFactory;
import com.example.mysterybook.daos.comment.ICommentDao;
import com.example.mysterybook.dto.comment.UploadCommentDto;

public class CommentService implements ICommentService {
    private static CommentService instance;
    ICommentDao commentDao = null;
    private CommentService() throws Exception {
        try {
            commentDao = DaoFactory.getInstance().getCommentDao();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static CommentService getInstance() throws Exception {
        if (instance == null) {
            instance = new CommentService();
        }
        return instance;
    }

    @Override
    public boolean uploadComment(UploadCommentDto dto) throws Exception {
        boolean result = false;
        if (commentDao != null) {
            result = commentDao.uploadComment(dto);
        }
        return false;
    }
}
