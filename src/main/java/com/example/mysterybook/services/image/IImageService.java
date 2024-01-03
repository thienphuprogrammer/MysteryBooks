package com.example.mysterybook.services.image;

import com.example.mysterybook.dto.image.UploadImageDto;
import com.example.mysterybook.models.Images;

import java.util.List;

public interface IImageService {
    List<Images> getImagesByPostId(int id) throws Exception;

    boolean deleteImage(UploadImageDto dto) throws Exception;
}
