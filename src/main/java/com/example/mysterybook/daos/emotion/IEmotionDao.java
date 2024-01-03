package com.example.mysterybook.daos.emotion;

import com.example.mysterybook.dto.like.UploadEmotion;
import com.example.mysterybook.models.Emotion;

public interface IEmotionDao {
    long getNumberOfEmotionsByPostId(int id);

    boolean createEmotion(Emotion emotion);

    Emotion getEmotionByPostIdAndUserId(int postId, int userId);

    boolean deleteEmotion(Emotion emotionCheck);

    boolean updateEmotion(Emotion emotion);
}