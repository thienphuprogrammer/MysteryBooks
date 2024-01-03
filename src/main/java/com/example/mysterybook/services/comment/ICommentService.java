package com.example.mysterybook.services.comment;

import com.example.mysterybook.dto.comment.UploadCommentDto;

public interface ICommentService {
    boolean uploadComment(UploadCommentDto dto) throws Exception;
}
