package com.example.mysterybook.daos.post;

import com.example.mysterybook.models.Posts;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IPostDao {
    ArrayList<Posts> getPosts() throws SQLException;

    List<Posts> getPostsByUserId(int userId) throws SQLException;

    int createPost(Posts dto) throws SQLException;

    Posts getPostById(int i);

    boolean updatePost(Posts post);

    boolean deletePost(Posts post);

    List<Posts> getAllPosts(int userId);
}
