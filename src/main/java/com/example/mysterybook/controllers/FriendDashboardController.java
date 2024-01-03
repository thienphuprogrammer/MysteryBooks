package com.example.mysterybook.controllers;

import com.example.mysterybook.dto.friend.RenderFriendRequestDto;
import com.example.mysterybook.dto.friend.RequestAddFriendDto;
import com.example.mysterybook.dto.friend.UnfriendDto;
import com.example.mysterybook.dto.user.RenderUserDto;
import com.example.mysterybook.services.friend.FriendService;
import com.example.mysterybook.services.friendrequest.FriendRequestService;
import com.example.mysterybook.services.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FriendDashboard", urlPatterns = {"/friends", "/profile/friend"})
public class FriendDashboardController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tab = req.getParameter("tab");
        try {
            if ("add".equals(tab)) {
                handlePostAddFriend(req, resp);
            } else if ("cancel".equals(tab)) {
                handlePostCancelFriend(req, resp);
            } else if ("accept".equals(tab)) {
                handlePostAcceptFriend(req, resp);
            } else if ("decline".equals(tab)) {
                handlePostDeclineFriend(req, resp);
            } else if ("unfriend".equals(tab)) {
                handlePostUnfriend(req, resp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
    }

    private void handlePostUnfriend(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getAttribute("userId").toString() == null ? "0" : req.getAttribute("userId").toString();
        String friendId = req.getParameter("friendId") == null ? "0" : req.getParameter("friendId");
        UnfriendDto dto = new UnfriendDto();
        dto.setUserId(Integer.parseInt(userId));
        dto.setFriendId(Integer.parseInt(friendId));
        try {
            boolean result = FriendService.getInstance().unfriend(dto);
            if (result) {
                req.setAttribute("success", "Unfriend successfully");
            } else {
                req.setAttribute("error", "Unfriend failed");
            }
            // get all url before
            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    private void handlePostDeclineFriend(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id") == null ? "0" : req.getParameter("id");
        String userId = req.getAttribute("userId").toString() == null ? "0" : req.getAttribute("userId").toString();
        String senderId = req.getParameter("senderId") == null ? "0" : req.getParameter("senderId");
        String receiverId = req.getParameter("receiverId") == null ? "0" : req.getParameter("receiverId");

        RequestAddFriendDto dto = new RequestAddFriendDto();
        dto.setUserId(Integer.parseInt(userId));
        dto.setSenderId(Integer.parseInt(senderId));
        dto.setReceiverId(Integer.parseInt(receiverId));
        dto.setId(Integer.parseInt(id));

        boolean result = FriendRequestService.getInstance().declineFriendRequest(dto);
        if (result) {
            req.setAttribute("success", "Decline add friend successfully");
        } else {
            req.setAttribute("error", "Decline add friend failed");
        }
        resp.sendRedirect(req.getHeader("referer"));
    }

    private void handlePostAcceptFriend(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            String id = req.getParameter("id") == null ? "0" : req.getParameter("id");
            String userId = req.getAttribute("userId").toString() == null ? "0" : req.getAttribute("userId").toString();
            String senderId = req.getParameter("senderId") == null ? "0" : req.getParameter("senderId");
            String receiverId = req.getParameter("receiverId") == null ? "0" : req.getParameter("receiverId");
            if ("0".equals(userId) && !userId.equals(receiverId)) {
                throw new Exception("You can't accept your request");
            }

            RequestAddFriendDto dto = new RequestAddFriendDto();
            dto.setUserId(Integer.parseInt(userId));
            dto.setSenderId(Integer.parseInt(senderId));
            dto.setReceiverId(Integer.parseInt(receiverId));
            dto.setId(Integer.parseInt(id));

            boolean result = FriendRequestService.getInstance().acceptFriendRequest(dto);
            if (result) {
                req.setAttribute("success", "Accept add friend successfully");
            } else {
                req.setAttribute("error", "Accept add friend failed");
            }
            resp.sendRedirect("../friends");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void handlePostCancelFriend(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            String userId = req.getAttribute("userId").toString() == null ? "0" : req.getAttribute("userId").toString();
            String friendId = req.getParameter("friendId") == null ? "0" : req.getParameter("friendId");
            RequestAddFriendDto dto = new RequestAddFriendDto();
            dto.setUserId(Integer.parseInt(userId));
            dto.setSenderId(Integer.parseInt(friendId));
            boolean result = FriendRequestService.getInstance().cancelAddFriend(dto);
            if (result) {
                req.setAttribute("success", "Cancel add friend successfully");
            } else {
                req.setAttribute("error", "Cancel add friend failed");
            }
            // send redirect to page before
            resp.sendRedirect("../friends");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void handlePostAddFriend(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            String userId = req.getAttribute("userId").toString() == null ? "0" : req.getAttribute("userId").toString();
            String friendId = req.getParameter("friendId") == null ? "0" : req.getParameter("friendId");
            RequestAddFriendDto dto = new RequestAddFriendDto();
            dto.setUserId(Integer.parseInt(userId));
            dto.setSenderId(Integer.parseInt(userId));
            dto.setReceiverId(Integer.parseInt(friendId));
            boolean result = FriendRequestService.getInstance().requestAddFriend(dto);
            if (result) {
                req.setAttribute("success", "Request add friend successfully");
            } else {
                req.setAttribute("error", "Request add friend failed");
            }
            resp.sendRedirect("../friends");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int userId = req.getAttribute("userId") == null ? 0 : (int) req.getAttribute("userId");
            List<RenderUserDto> listUser = UserService.getInstance().getUsersExceptCurrentAndFriends(userId);
            req.setAttribute("listUser", listUser);

            List<RenderFriendRequestDto> listFriendRequest = FriendRequestService.getInstance().getFriendRequestByUserId(userId);
            req.setAttribute("listFriendRequest", listFriendRequest);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        req.getRequestDispatcher("page/friend/FriendPageDashboard.jsp").forward(req, resp);
    }
}
