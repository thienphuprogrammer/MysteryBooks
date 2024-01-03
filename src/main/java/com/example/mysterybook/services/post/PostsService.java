package com.example.mysterybook.services.post;

import com.example.mysterybook.daos.DaoFactory;
import com.example.mysterybook.daos.post.IPostDao;
import com.example.mysterybook.dto.comment.RenderCommentDto;
import com.example.mysterybook.dto.post.RenderPostDto;
import com.example.mysterybook.dto.post.UploadPostDto;
import com.example.mysterybook.models.*;

import java.util.ArrayList;
import java.util.List;

public class PostsService implements IPostsService {
    private static PostsService instance = null;
    IPostDao postsDao;

    private PostsService() throws Exception {
        postsDao = DaoFactory.getInstance().getPostsDao();
    }

    public static PostsService getInstance() throws Exception {
        if (instance == null) {
            instance = new PostsService();
        }
        return instance;
    }

    @Override
    public boolean createPost(UploadPostDto dto) throws Exception {
        boolean result = false;
        if (postsDao != null) {
            Posts post = new Posts();
            post.loadFromDto(dto);
            if (dto.getUrlImages() == null) {
                dto.setUrlImages(new ArrayList<>());
            }
            List<Images> images = new ArrayList<>();
            for (String image : dto.getUrlImages()) {
                Images img = new Images();
                img.setPostId(dto.getId());
                img.setUserId(dto.getUserId());
                img.setUrlImg(image);
                images.add(img);
            }
            int postId = postsDao.createPost(post);
            if (postId > 0) {
                for (Images image : images) {
                    image.setPostId(postId);
                    result = DaoFactory.getInstance().getImageDao().createImage(image);
                }
            }
        }
        return result;
    }

    @Override
    public List<RenderPostDto> getPostsByUserId(int userId) throws Exception {
        List<RenderPostDto> result = new ArrayList<>();
        if (postsDao != null) {
            List<Posts> posts = postsDao.getPostsByUserId(userId);
            for (Posts post : posts) {
                    List<Images> images = DaoFactory.getInstance().getImageDao().getImagesByPostId(post.getId());
                    RenderPostDto dto = new RenderPostDto();
                    User user = DaoFactory.getInstance().getUserDao().getUserById(post.getUserId());

                    List<RenderCommentDto> renderCommentDto = new ArrayList<>();
                    List<Comments> comments = DaoFactory.getInstance().getCommentDao().getCommentsByPostId(post.getId());
                    for (Comments comment : comments) {
                        User userComment = DaoFactory.getInstance().getUserDao().getUserById(comment.getUserId());
                        RenderCommentDto renderComment = new RenderCommentDto();
                        renderComment.loadFromModel(comment, userComment);
                        renderCommentDto.add(renderComment);
                    }
                    Emotion emotion = DaoFactory.getInstance().getEmotionDao().getEmotionByPostIdAndUserId(post.getId(), userId);
                    if (emotion != null) {
                        dto.setIsLiked(1);
                    }
                    dto.loadFromModel(post, images, user, comments, renderCommentDto);
                    dto.setNumberOfComments(comments.size());
                    dto.setNumberOfLikes(DaoFactory.getInstance().getEmotionDao().getNumberOfEmotionsByPostId(post.getId()));
                    result.add(dto);

            }
        }
        return result;
    }

    @Override
    public RenderPostDto getPostById(int i) throws Exception {
        RenderPostDto result = new RenderPostDto();
        if (postsDao != null) {
                Posts post = postsDao.getPostById(i);
                List<Images> images = DaoFactory.getInstance().getImageDao().getImagesByPostId(post.getId());
                User user = DaoFactory.getInstance().getUserDao().getUserById(post.getUserId());
                List<Comments> comments = DaoFactory.getInstance().getCommentDao().getCommentsByPostId(post.getId());
                result.loadFromModel(post, images, user, comments, null);

        }
        return result;
    }

    @Override
    public boolean updatePost(UploadPostDto dto) throws Exception {
        boolean result = false;
        if (postsDao != null) {
            Posts checkPost = postsDao.getPostById(dto.getId());
            if (checkPost == null) {
                throw new RuntimeException("Post not found");
            }
            if (checkPost.getUserId() != dto.getUserId()) {
                throw new RuntimeException("You don't have permission to update this post");
            }
            Posts post = new Posts();
            post.loadFromDto(dto);
            List<Images> images = new ArrayList<>();
            if (dto.getUrlImages() != null) {
                for (String image : dto.getUrlImages()) {
                    Images img = new Images();
                    img.setPostId(dto.getId());
                    img.setUserId(dto.getUserId());
                    img.setUrlImg(image);
                    images.add(img);
                }
            }
            result = postsDao.updatePost(post);
            if (result && !images.isEmpty()) {
                for (Images image : images) {
                    result = DaoFactory.getInstance().getImageDao().createImage(image);
                }
            }
        }
        return result;
    }

    @Override
    public boolean deletePost(UploadPostDto dto) throws Exception {
        boolean result = false;
        if (postsDao != null) {
            Posts posts = postsDao.getPostById(dto.getId());
            if (posts == null) {
                throw new Exception("Post not found");
            }
            if (posts.getUserId() != dto.getUserId()) {
                throw new Exception("You don't have permission to delete this post");
            }
            result = postsDao.deletePost(posts);
        }
        return result;
    }

    @Override
    public List<RenderPostDto> getALlPosts(int userId) throws Exception {
        List<RenderPostDto> result = new ArrayList<>();
        if (postsDao != null) {
            List<Posts> posts = postsDao.getAllPosts(userId);
            for (Posts post : posts) {
                List<Images> images = DaoFactory.getInstance().getImageDao().getImagesByPostId(post.getId());
                RenderPostDto dto = new RenderPostDto();
                User user = DaoFactory.getInstance().getUserDao().getUserById(post.getUserId());

                List<RenderCommentDto> renderCommentDto = new ArrayList<>();
                List<Comments> comments = DaoFactory.getInstance().getCommentDao().getCommentsByPostId(post.getId());
                for (Comments comment : comments) {
                    User userComment = DaoFactory.getInstance().getUserDao().getUserById(comment.getUserId());
                    RenderCommentDto renderComment = new RenderCommentDto();
                    renderComment.loadFromModel(comment, userComment);
                    renderCommentDto.add(renderComment);
                }
                Emotion emotion = DaoFactory.getInstance().getEmotionDao().getEmotionByPostIdAndUserId(post.getId(), userId);
                if (emotion != null) {
                    dto.setIsLiked(1);
                }
                dto.loadFromModel(post, images, user, comments, renderCommentDto);
                dto.setNumberOfComments(comments.size());
                dto.setNumberOfLikes(DaoFactory.getInstance().getEmotionDao().getNumberOfEmotionsByPostId(post.getId()));
                result.add(dto);

            }
        }
        return result;
    }
}
