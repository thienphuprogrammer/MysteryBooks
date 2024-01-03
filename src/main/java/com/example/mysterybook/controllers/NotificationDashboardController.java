package com.example.mysterybook.controllers;

import com.example.mysterybook.dto.message.MessageFriendsDto;
import com.example.mysterybook.dto.message.RenderMessageDto;
import com.example.mysterybook.dto.message.UploadMessageDto;
import com.example.mysterybook.dto.notification.RenderNotification;
import com.example.mysterybook.services.message.MessageService;
import com.example.mysterybook.services.notifiction.NotificationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "NotificationDashboardController", urlPatterns = "/notifications")
public class NotificationDashboardController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getAttribute("userId") != null ? req.getAttribute("userId").toString() : "0";

        try {
            List<RenderNotification> notifications = NotificationService.getInstance().getAllNotifications(Integer.parseInt(userId));
            req.setAttribute("notifications", notifications);
            req.getRequestDispatcher("page/notification/NotificationDashboard.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
    }
}
