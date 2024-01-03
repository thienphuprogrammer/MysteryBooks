package com.example.mysterybook.daos.comment;

import com.example.mysterybook.dto.comment.UploadCommentDto;
import com.example.mysterybook.models.Comments;

import java.util.List;

public interface ICommentDao {

    boolean uploadComment(UploadCommentDto dto) throws Exception;

    List<Comments> getCommentsByPostId(int id) throws Exception;
}
