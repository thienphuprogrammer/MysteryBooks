package com.example.mysterybook.services.image;

import com.example.mysterybook.daos.DaoFactory;
import com.example.mysterybook.daos.image.IImageDao;
import com.example.mysterybook.dto.image.UploadImageDto;
import com.example.mysterybook.models.Images;

import java.util.List;

public class ImageService implements IImageService {
    private static ImageService instance;
    IImageDao imageDao = null;
    public ImageService() throws Exception {
        imageDao = DaoFactory.getInstance().getImageDao();
    }
    public static ImageService getInstance() throws Exception {
        if (instance == null) {
            instance = new ImageService();
        }
        return instance;
    }

    @Override
    public List<Images> getImagesByPostId(int id) throws Exception {
        List<Images> result = null;
        if (imageDao != null) {
            result = imageDao.getImagesByPostId(id);
        }
        return result;
    }

    @Override
    public boolean deleteImage(UploadImageDto dto) throws Exception {
        boolean result = false;
        if (imageDao != null) {
            //check user id
            Images imageCheck = imageDao.getImageById(dto.getId());
            if (imageCheck.getUserId() != dto.getUserId()) {
                throw new Exception("You can't delete this image");
            }
            if (imageCheck.getPostId() != dto.getPostId()) {
                throw new Exception("You can't delete this image");
            }

            Images image = new Images();
            image.loadFromDto(dto);
            result = imageDao.deleteImage(image);
        }
        return result;
    }
}
