package com.example.mysterybook.daos.image;

import com.example.mysterybook.models.Images;

import java.util.ArrayList;

public interface IImageDao {
    ArrayList<Images> getImagesByPostId(int postId) throws Exception;

    boolean createImage(Images image) throws Exception;

    boolean deleteImage(Images image) throws Exception;

    Images getImageById(int id);
}
