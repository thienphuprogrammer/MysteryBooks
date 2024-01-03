package com.example.mysterybook.controllers.post.emotion;

import com.example.mysterybook.dto.like.UploadEmotion;
import com.example.mysterybook.services.emtion.EmotionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LikeController", urlPatterns = {"/home/like",
        "/profile/like",
        "/friends/like"
})
public class EmotionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tab = req.getParameter("tab");
        try {
            if ("create".equals(tab)) {
                handlePostCreate(req, resp);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
    }

    private void handlePostCreate(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String userId = req.getAttribute("userId").toString() == null ? "0" : req.getAttribute("userId").toString();
            String postId = req.getParameter("postId") == null ? "0" : req.getParameter("postId");
            String groupId = req.getParameter("groupId") == null ? "0" : req.getParameter("groupId");
            String commentId = req.getParameter("commentId") == null ? "0" : req.getParameter("commentId");
            String type = req.getParameter("type") == null ? "0" : req.getParameter("type");

            UploadEmotion emotion = new UploadEmotion();
            emotion.setUserId(Integer.parseInt(userId));
            emotion.setPostId(Integer.parseInt(postId));
            emotion.setGroupId(Integer.parseInt(groupId));
            emotion.setCommentId(Integer.parseInt(commentId));
            emotion.setType(type);

            EmotionService.getInstance().createEmotion(emotion);
            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
    }
}
