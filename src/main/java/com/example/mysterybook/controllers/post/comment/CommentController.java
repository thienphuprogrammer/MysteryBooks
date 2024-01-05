package com.example.mysterybook.controllers.post.comment;

import com.example.mysterybook.dto.comment.UploadCommentDto;
import com.example.mysterybook.services.comment.CommentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CommentController", urlPatterns = {"/home/comment",
        "/profile/comment",
        "/friends/comment"
})
public class CommentController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("create".equals(action)) {
            handleUploadComment(req, resp);
        } else {
            resp.sendRedirect("Comment?action=comment");
        }
    }

    private void handleUploadComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UploadCommentDto dto = new UploadCommentDto();
            String userId = req.getAttribute("userId").toString();
            if (userId != null) {
                dto.setUserId(Integer.parseInt(userId));
            }
            String postId = req.getParameter("postId");
            if (postId != null) {
                dto.setPostId(Integer.parseInt(postId));
            }
            String content = req.getParameter("comment");
            dto.setContent(content == null ? "" : content);
            dto.setPostId(Integer.parseInt(req.getParameter("postId")));
            CommentService.getInstance().uploadComment(dto);
            // redirect to page before
            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
    }
}
