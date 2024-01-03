package com.example.mysterybook.services.emtion;

import com.example.mysterybook.dto.like.UploadEmotion;

public interface IEmotionService {
    boolean createEmotion(UploadEmotion emotion);
}
