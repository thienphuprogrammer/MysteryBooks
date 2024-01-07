package com.example.mysterybook.controllers.post.post;

import com.example.mysterybook.dto.image.UploadImageDto;
import com.example.mysterybook.dto.post.RenderPostDto;
import com.example.mysterybook.dto.post.UploadPostDto;
import com.example.mysterybook.services.image.ImageService;
import com.example.mysterybook.services.post.PostsService;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(
        name = "PostController",
        urlPatterns = {"/home/post", "/profile/post", "/friends/post"}
)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class PostController extends HttpServlet {

    public UploadPostDto getPostFromWeb(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UploadPostDto dto = new UploadPostDto();
        String userId = req.getAttribute("userId").toString();
        if (userId != null) {
            dto.setUserId(Integer.parseInt(userId));
        }
        String groupId = req.getParameter("groupId");
        if (groupId != null) {
            dto.setGroupId(Integer.parseInt(groupId));
        }
        dto.setContent(req.getParameter("content") == null ? "" : req.getParameter("content"));
        dto.setTitle(req.getParameter("title") == null ? "" : req.getParameter("title"));
        // get multi-part file
        ArrayList<Part> listParts = req.getParts()
                .stream()
                .filter(part -> "urlImages".equals(part.getName()))
                .filter(part -> part.getSize() > 0)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        if (!listParts.isEmpty()) {
            String[] urlImages = new String[listParts.size()];
            for (int i = 0; i < listParts.size(); i++) {
                Part part = listParts.get(i);
                String fileName = part.getSubmittedFileName();
                String extension = fileName.substring(fileName.lastIndexOf("."));
                String urlImage = "src/images/" + System.currentTimeMillis() + extension;
                part.write(getServletContext().getRealPath("") + urlImage);
                urlImages[i] = urlImage;
            }
            dto.setUrlImages(new ArrayList<>(Arrays.asList(urlImages)));
        }
        int visibility = Integer.parseInt(req.getParameter("visibility"));
        dto.setVisibility(UploadPostDto.VISIBILITY.values()[visibility].toString().toLowerCase());
        return dto;
    }

    private void handleCreatePost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        UploadPostDto dto = getPostFromWeb(req, resp);
        boolean result = PostsService.getInstance().createPost(dto);
        if (result) {
            req.setAttribute("success", "Create post successfully");
        } else {
            req.setAttribute("error", "Create post failed");
        }
        // send redirect to page before
        resp.sendRedirect(req.getHeader("referer"));
    }

    private void handleDeletePost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String postId = req.getParameter("postId");
        if (postId == null) {
            throw new RuntimeException("Post id is null");
        }
        UploadPostDto dto = new UploadPostDto();
        dto.setId(Integer.parseInt(postId));
        dto.setUserId(Integer.parseInt(req.getAttribute("userId").toString()));
        boolean result = PostsService.getInstance().deletePost(dto);
        if (result) {
            req.setAttribute("success", "Delete post successfully");
        } else {
            req.setAttribute("error", "Delete post failed");
        }
        resp.sendRedirect(req.getHeader("referer"));
    }

    private void handleUpdatePost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        UploadPostDto dto = getPostFromWeb(req, resp);
        // get Parameter has value include deleted image
        dto.setId(Integer.parseInt(req.getParameter("id")));
            boolean result = PostsService.getInstance().updatePost(dto);

        RenderPostDto post = PostsService.getInstance().getPostById(dto.getId());
        req.setAttribute("post", post);
        if (result) {
            req.setAttribute("success", "Update post successfully");
        } else {
            req.setAttribute("error", "Update post failed");
        }
        // send redirect to page before
        req.getRequestDispatcher("../../page/posts/UpdatePostPageDashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            if ("create".equals(action)) {
                handleCreatePost(req, resp);
            } else if ("update".equals(action)) {
                 handleUpdatePost(req, resp);
            } else if ("delete".equals(action)) {
                 handleDeletePost(req, resp);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String tab = req.getParameter("tab");
            if ("update".equals(tab)) {
                String action = req.getParameter("action");
                if ("delete".equals(action)) {
                    handleGetDeleteImage(req, resp);
                } else {
                    handleGetUpdatePost(req, resp);
                }
            } else if ("delete".equals(tab)) {
                handleGetDeletePost(req, resp);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
    }

    private void handleGetDeleteImage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String imageId = req.getParameter("imageId");
        if (imageId == null) {
            throw new Exception("Image id is null");
        }
        String postId = req.getParameter("postId");
        if (postId == null) {
            throw new Exception("Post id is null");
        }
        String userId = req.getAttribute("userId").toString();
        if (userId == null) {
            throw new Exception("User id is null");
        }
        UploadImageDto dto = new UploadImageDto();
        dto.setId(Integer.parseInt(imageId));
        dto.setPostId(Integer.parseInt(postId));
        dto.setUserId(Integer.parseInt(userId));
        boolean result = ImageService.getInstance().deleteImage(dto);
        if (result) {
            req.setAttribute("success", "Delete image successfully");
        } else {
            req.setAttribute("error", "Delete image failed");
        }
        // send redirect to page before
        resp.sendRedirect(req.getHeader("referer"));
    }

    private void handleGetDeletePost(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("tab", "delete");
    }

    private void handleGetUpdatePost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String postId = req.getParameter("postId");
        if (postId == null) {
            throw new RuntimeException("Post id is null");
        }
        RenderPostDto post = PostsService.getInstance().getPostById(Integer.parseInt(postId));
        req.setAttribute("post", post);
        req.getRequestDispatcher("../page/posts/UpdatePostPageDashboard.jsp").forward(req, resp);
    }
}
