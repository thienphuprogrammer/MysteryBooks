package com.example.mysterybook.services.emtion;

import com.example.mysterybook.daos.DaoFactory;
import com.example.mysterybook.daos.emotion.IEmotionDao;
import com.example.mysterybook.dto.like.UploadEmotion;
import com.example.mysterybook.models.Emotion;

public class EmotionService implements IEmotionService {
    private static EmotionService instance = null;
    IEmotionDao emotionDao = null;

    private EmotionService() throws Exception {
        emotionDao = DaoFactory.getInstance().getEmotionDao();
    }

    public static EmotionService getInstance() throws Exception {
        if (instance == null) {
            instance = new EmotionService();
        }

        return instance;
    }

    @Override
    public boolean createEmotion(UploadEmotion dto) {
        boolean result = false;
        if (emotionDao != null) {
            Emotion emotion = new Emotion();
            emotion.loadFromDto(dto);
            Emotion emotionCheck = emotionDao.getEmotionByPostIdAndUserId(dto.getPostId(), dto.getUserId());
            if (emotionCheck != null) {
                emotion.setId(emotionCheck.getId());
                result = emotionDao.deleteEmotion(emotion);
            } else {
                result = emotionDao.createEmotion(emotion);
            }
        }
        return result;
    }
}
