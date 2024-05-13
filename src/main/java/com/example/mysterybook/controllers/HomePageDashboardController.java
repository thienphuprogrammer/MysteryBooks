package com.example.mysterybook.controllers;

import com.example.mysterybook.dto.friend.RenderFriendRequestDto;
import com.example.mysterybook.dto.post.RenderPostDto;

import com.example.mysterybook.dto.user.RenderUserDto;
import com.example.mysterybook.services.friendrequest.FriendRequestService;
import com.example.mysterybook.services.post.PostsService;
import com.example.mysterybook.services.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomePageDashboardController", urlPatterns = {"/home"}, loadOnStartup = 1, asyncSupported = true)
public class HomePageDashboardController extends HttpServlet {
    void handGetInfo(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int userId = (int) req.getAttribute("userId") == 0 ? 0 : (int) req.getAttribute("userId");

        if (action.equals("search")) {
            String search = req.getParameter("search") == null ? "" : req.getParameter("search");
            List<RenderPostDto> postsDto = null;
            List<RenderUserDto> listUser = null;
            List<RenderFriendRequestDto> listFriendRequest = null;
            try {
                if (search.isEmpty()) {
                    postsDto = PostsService.getInstance().getALlPosts(userId);
                } else {
                    postsDto = PostsService.getInstance().getALlPostsBySearch(userId, search);
                }
                listUser = UserService.getInstance().getUsersExceptCurrentAndFriends(userId);

                listFriendRequest = FriendRequestService.getInstance().getFriendRequestByUserId(userId);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            req.setAttribute("listUser", listUser);
            req.setAttribute("listFriendRequest", listFriendRequest);
            req.setAttribute("posts", postsDto);
            req.getRequestDispatcher("page/home/HomeDashboard.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RenderPostDto> postsDto = null;
        int userId = (int) req.getAttribute("userId") == 0 ? 0 : (int) req.getAttribute("userId");
        List<RenderUserDto> listUser = null;
        List<RenderFriendRequestDto> listFriendRequest = null;
        try {
            postsDto = PostsService.getInstance().getALlPosts(userId);
            listUser = UserService.getInstance().getUsersExceptCurrentAndFriends(userId);

            listFriendRequest = FriendRequestService.getInstance().getFriendRequestByUserId(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        req.setAttribute("listUser", listUser);
        req.setAttribute("listFriendRequest", listFriendRequest);
        req.setAttribute("posts", postsDto);
        req.getRequestDispatcher("page/home/HomeDashboard.jsp").forward(req, resp);
    }
}
