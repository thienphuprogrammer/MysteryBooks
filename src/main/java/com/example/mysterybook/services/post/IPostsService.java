package com.example.mysterybook.services.post;

import com.example.mysterybook.dto.post.RenderPostDto;
import com.example.mysterybook.dto.post.UploadPostDto;
import com.example.mysterybook.models.Posts;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IPostsService {
    boolean createPost(UploadPostDto dto) throws Exception;

    List<RenderPostDto> getPostsByUserId(int userId) throws Exception;

    RenderPostDto getPostById(int i) throws Exception;

    boolean updatePost(UploadPostDto dto) throws Exception;

    boolean deletePost(UploadPostDto dto) throws Exception;

    List<RenderPostDto> getALlPosts(int userId) throws Exception;

    List<RenderPostDto> getPostsForGuest(int i, int i1) throws Exception;

    List<RenderPostDto> getALlPostsBySearch(int userId, String search) throws Exception;
}
