package com.example.mysterybook.controllers;

import com.example.mysterybook.dto.user.*;
import com.example.mysterybook.services.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
@WebServlet(name = "SettingDashboardController", value = "/settings")
public class SettingDashboardController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";
        try {
            if ("updateAvatar".equals(action)) {
                handlePostUpdateAvatar(req, resp);
            } else if ("updatePassword".equals(action)) {
                handlePostUpdatePassword(req, resp);
            } else if ("updateName".equals(action)) {
                handlePostUpdateName(req, resp);
            } else if ("updateInfo".equals(action)) {
                handlePostUpdateInfo(req, resp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
    }

    private void handlePostUpdateInfo(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getAttribute("userId").toString() == null ? "0" : req.getAttribute("userId").toString();
        String phoneNumber = req.getParameter("phoneNumber") != null ? req.getParameter("phoneNumber") : "";
        String address = req.getParameter("address") != null ? req.getParameter("address") : "";
        String dateOfBirth = req.getParameter("dateOfBirth") != null ? req.getParameter("dateOfBirth") : "";
        String bio = req.getParameter("bio") != null ? req.getParameter("bio") : "";
        String interests = req.getParameter("interest") != null ? req.getParameter("interest") : "";

        UpdateInfoUserDto userDto = new UpdateInfoUserDto();
        userDto.setId(Integer.parseInt(userId));
        userDto.setPhoneNumber(phoneNumber);
        userDto.setAddress(address);
        userDto.setDateOfBirth(dateOfBirth);
        userDto.setBio(bio);
        userDto.setInterests(interests);

        try {
            boolean result = UserService.getInstance().updateInfoUser(userDto);
            if (result) {
                req.setAttribute("success", "Update info successfully");
            } else {
                req.setAttribute("error", "Update info failed");
            }
            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
    }

    private void handlePostUpdateName(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getAttribute("userId").toString() == null ? "0" : req.getAttribute("userId").toString();
        String fullName = req.getParameter("fullName") != null ? req.getParameter("fullName") : "";

        UpdateFullNameUserDto userDto = new UpdateFullNameUserDto();
        userDto.setId(Integer.parseInt(userId));
        userDto.setFullName(fullName);

        try {
            boolean result = UserService.getInstance().updateUserFullName(userDto);
            if (result) {
                req.setAttribute("success", "Update name successfully");
            } else {
                req.setAttribute("error", "Update name failed");
            }
            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
    }

    private void handlePostUpdatePassword(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getAttribute("userId").toString() == null ? "0" : req.getAttribute("userId").toString();
        String oldPassword = req.getParameter("oldPassword") != null ? req.getParameter("oldPassword") : "";
        String newPassword = req.getParameter("newPassword") != null ? req.getParameter("newPassword") : "";
        String confirmPassword = req.getParameter("confirmPassword") != null ? req.getParameter("confirmPassword") : "";

        UpdatePasswordUserDto userDto = new UpdatePasswordUserDto();
        userDto.setId(Integer.parseInt(userId));
        userDto.setOldPassword(oldPassword);
        userDto.setNewPassword(newPassword);
        userDto.setConfirmPassword(confirmPassword);

        try {
            boolean result = UserService.getInstance().updateUserPassword(userDto);
            if (result) {
                req.setAttribute("success", "Update password successfully");
            } else {
                req.setAttribute("error", "Update password failed");
            }
            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
    }

    private void handlePostUpdateAvatar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getAttribute("userId").toString() == null ? "0" : req.getAttribute("userId").toString();
        Part filePart = req.getPart("avatar") != null ? req.getPart("avatar") : null;
        if (filePart == null) {
            req.setAttribute("error", "Avatar is null");
            return;
        }
        String profilePicture = filePart.getSubmittedFileName();
        String extension = profilePicture.substring(profilePicture.lastIndexOf(".") + 1);
        String allowedExtension = "jpg,jpeg,png";
        if (!allowedExtension.contains(extension)) {
            req.setAttribute("error", "Avatar is not valid");
            return;
        }

        String path = "src/images/avatar/" + System.currentTimeMillis() + "." + extension;
        filePart.write(getServletContext().getRealPath("") + path);
        profilePicture = path;

        UpdateAvatarUserDto userDto = new UpdateAvatarUserDto();
        userDto.setId(Integer.parseInt(userId));
        userDto.setAvatar(profilePicture);

        try {
            boolean result = UserService.getInstance().updateUserAvatar(userDto);
            if (result) {
                req.setAttribute("success", "Update avatar successfully");
            } else {
                req.setAttribute("error", "Update avatar failed");
            }
            resp.sendRedirect(req.getHeader("referer"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String userId = req.getAttribute("userId").toString() == null ? "0" : req.getAttribute("userId").toString();
            RenderUserDto userDto = UserService.getInstance().getUserById(Integer.parseInt(userId));
            req.setAttribute("user", userDto);
            req.getRequestDispatcher("page/setting/SettingPage.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("error", e.getMessage());
        }
    }
}
