package com.example.mysterybook.controllers;

import com.example.mysterybook.dto.friend.RenderFriendDto;
import com.example.mysterybook.dto.friend.RenderFriendRequestDto;
import com.example.mysterybook.dto.friend.RequestAddFriendDto;
import com.example.mysterybook.dto.post.RenderPostDto;
import com.example.mysterybook.dto.post.UploadPostDto;
import com.example.mysterybook.dto.user.RenderUserDto;
import com.example.mysterybook.services.friend.FriendService;
import com.example.mysterybook.services.friendrequest.FriendRequestService;
import com.example.mysterybook.services.post.PostsService;
import com.example.mysterybook.services.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ProfileDashboard", urlPatterns = "/profile")
public class ProfileDashboardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String id = req.getParameter("id") == null ? "0" : req.getParameter("id");
            String userId = req.getAttribute("userId").toString() == null ? "0" : req.getAttribute("userId").toString();
            if ("0".equals(id)) {
                id = req.getAttribute("userId").toString();
                resp.sendRedirect("/profile?id=" + id);
                return;
            }

            try {
                handleGetProfile(req, resp);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                req.setAttribute("error", e.getMessage());
            }
    }

    private void handleGetProfile(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id") == null ? "0" : req.getParameter("id");

        RenderUserDto user = UserService.getInstance().getUserById(Integer.parseInt(id));
        List<RenderFriendDto> friends = FriendService.getInstance().getFriendsByUserId(Integer.parseInt(id));
        List<RenderPostDto> posts = PostsService.getInstance().getPostsByUserId(Integer.parseInt(id));

        req.setAttribute("user", user);
        req.setAttribute("friends", friends);
        req.setAttribute("posts", posts);
        if (req.getAttribute("userId").toString().equals(id)) {
            req.getRequestDispatcher("page/profile/ProfileOwnerPageDashboard.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("page/profile/ProfileGuestPageDashboard.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
