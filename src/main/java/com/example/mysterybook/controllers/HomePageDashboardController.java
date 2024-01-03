package com.example.mysterybook.controllers;

import com.example.mysterybook.dto.post.RenderPostDto;

import com.example.mysterybook.services.post.PostsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomePageDashboardController", urlPatterns = "/home")
public class HomePageDashboardController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RenderPostDto> postsDto = null;
        int userId = (int) req.getAttribute("userId");
        try {
            postsDto = PostsService.getInstance().getALlPosts(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        req.setAttribute("posts", postsDto);
        req.getRequestDispatcher("page/home/HomeDashboard.jsp").forward(req, resp);
    }
}
