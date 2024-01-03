package com.example.mysterybook.daos.image;

import com.example.mysterybook.models.Images;
import com.example.mysterybook.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ImageDao implements IImageDao {
    private static Connection connection = null;

    public ImageDao() throws Exception {
        connection = DatabaseUtil.getInstance().getConnection();
    }

    @Override
    public ArrayList<Images> getImagesByPostId(int postId) throws Exception {
        ArrayList<Images> images = new ArrayList<>();
        try {
            String query = "SELECT * FROM images WHERE post_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, postId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Images image = new Images();
                image.setId(resultSet.getInt("id"));
                image.setPostId(resultSet.getInt("post_id"));
                image.setUserId(resultSet.getInt("user_id"));
                image.setUrlImg(resultSet.getString("image_url"));
                images.add(image);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return images;
    }

    @Override
    public boolean createImage(Images image) throws Exception {
        boolean result = false;
        try {
            String query = "INSERT INTO images (post_id, user_id, image_url) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, image.getPostId());
            statement.setInt(2, image.getUserId());
            statement.setString(3, image.getUrlImg());
            statement.executeUpdate();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean deleteImage(Images image) throws Exception {
        boolean result = false;
        try {
            String query = "DELETE FROM images WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, image.getId());
            statement.executeUpdate();
            result = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return result;
    }

    @Override
    public Images getImageById(int id) {
        Images image = null;
        try {
            String query = "SELECT * FROM images WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                image = new Images();
                image.setId(resultSet.getInt("id"));
                image.setPostId(resultSet.getInt("post_id"));
                image.setUserId(resultSet.getInt("user_id"));
                image.setUrlImg(resultSet.getString("image_url"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return image;
    }
}
