package com.example.mysterybook.controllers;

import com.example.mysterybook.dto.friend.RenderFriendDto;
import com.example.mysterybook.dto.message.MessageFriendsDto;
import com.example.mysterybook.dto.message.RenderMessageDto;
import com.example.mysterybook.dto.message.UploadMessageDto;
import com.example.mysterybook.services.friend.FriendService;
import com.example.mysterybook.services.message.MessageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MessageDashboardController", urlPatterns = "/messages")
public class MessageDashboardController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("create".equals(action)) {
                handlePostCreateMessage(req, resp);
            } else if ("delete".equals(action)) {
                handlePostDeleteMessage(req, resp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", e.getMessage());

        }
    }

    private void handlePostDeleteMessage(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getAttribute("userId") != null ? req.getAttribute("userId").toString() : "0";
        String id = req.getParameter("id") != null ? req.getParameter("id") : "0";
        String friendId = req.getParameter("friendId") != null ? req.getParameter("friendId") : "0";

        try {
            boolean result = MessageService.getInstance().deleteMessage(Integer.parseInt(id), Integer.parseInt(userId));
            if (result) {
                resp.sendRedirect("messages?friendId=" + friendId);
            } else {
                throw new Exception("Failed to delete message");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
    }

    private void handlePostCreateMessage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getAttribute("userId") != null ? req.getAttribute("userId").toString() : "0";
        String friendId = req.getParameter("friendId") != null ? req.getParameter("friendId") : "0";
        String content = req.getParameter("content").trim().replaceAll("\\s+", " ");
        // check content must not empty and not contain only space
        if (content.isEmpty() || content.isBlank()) {
            resp.sendRedirect("messages?friendId=" + friendId);
            return;
        }

        UploadMessageDto dto = new UploadMessageDto();
        dto.setUserId(Integer.parseInt(userId));
        dto.setFriendId(Integer.parseInt(friendId));
        dto.setContent(content);

        try {
            boolean result = MessageService.getInstance().createMessage(dto);
            if (result) {
                resp.sendRedirect("messages?friendId=" + friendId);
            } else {
                throw new Exception("Failed to create message");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RenderMessageDto dto = new RenderMessageDto();
        String userId = req.getAttribute("userId") != null ? req.getAttribute("userId").toString() : "0";
        String friendId = req.getParameter("friendId") != null ? req.getParameter("friendId") : "0";
        dto.setUserId(Integer.parseInt(userId));
        dto.setFriendId(Integer.parseInt(friendId));

        try {
            List<RenderMessageDto> messagesDto =  MessageService.getInstance().getALlMessages(dto);
            List<MessageFriendsDto> friendsDto = MessageService.getInstance().getFriendsByUserId(Integer.parseInt(userId));
            req.setAttribute("messages", messagesDto);
            req.setAttribute("friends", friendsDto);
            req.setAttribute("friendId", friendId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
        req.getRequestDispatcher("page/message/MessagePageDashboard.jsp").forward(req, resp);
    }
}
