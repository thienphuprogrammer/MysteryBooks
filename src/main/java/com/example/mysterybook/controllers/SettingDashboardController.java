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
    HttpServletRequest setAllOddParams(HttpServletRequest req) {
        for (String key : req.getParameterMap().keySet()) {
            if (Integer.parseInt(key) % 2 == 0) {
                req.setAttribute(key, req.getParameter(key));
            }
        }
        return req;
    }
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

    private void handlePostUpdateInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getAttribute("userId").toString() == null ? "0" : req.getAttribute("userId").toString();
        String phoneNumber = req.getParameter("phoneNumber") != null ? req.getParameter("phoneNumber") : "";
        String address = req.getParameter("address") != null ? req.getParameter("address") : "";
        String dateOfBirth = req.getParameter("dateOfBirth") != null ? req.getParameter("dateOfBirth") : "";
        String bio = req.getParameter("bio") != null ? req.getParameter("bio") : "";
        String interests = req.getParameter("interest") != null ? req.getParameter("interest") : "";

        if (dateOfBirth.isEmpty() || dateOfBirth.isBlank()) {
            req.setAttribute("errorUpdateInfo", "Date of birth is not valid");
            req.getRequestDispatcher("page/setting/SettingPage.jsp").forward(req, resp);
            return;
        }

        // check date of birth must be in the past
        if (dateOfBirth.compareTo(java.time.LocalDate.now().toString()) > 0) {
            req.setAttribute("errorUpdateInfo", "Date of birth must be in the past");
            req.getRequestDispatcher("page/setting/SettingPage.jsp").forward(req, resp);
            return;
        }

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
                req.setAttribute("successUpdateInfo", "Update info successfully");
            } else {
                req.setAttribute("errorUpdateInfo", "Update info failed");
            }
            RenderUserDto user = UserService.getInstance().getUserById(Integer.parseInt(userId));
            req.setAttribute("user", user);
            req.getRequestDispatcher("page/setting/SettingPage.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("errorUpdateInfo", e.getMessage());
        }
    }

    private void handlePostUpdateName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getAttribute("userId").toString() == null ? "0" : req.getAttribute("userId").toString();
        String fullName = req.getParameter("fullName") != null ? req.getParameter("fullName") : "";
        req.setAttribute("fullName", fullName);
        if (fullName.isEmpty() || fullName.isBlank()) {
            req.setAttribute("errorUpdateName", "Full name is not valid");
            req.getRequestDispatcher("page/setting/SettingPage.jsp").forward(req, resp);
            return;
        }
        UpdateFullNameUserDto userDto = new UpdateFullNameUserDto();
        userDto.setId(Integer.parseInt(userId));
        userDto.setFullName(fullName);

        try {
            boolean result = UserService.getInstance().updateUserFullName(userDto);
            if (result) {
                req.setAttribute("successUpdateName", "Update name successfully");
            } else {
                req.setAttribute("errorUpdateName", "Update name failed");
            }
            RenderUserDto user = UserService.getInstance().getUserById(Integer.parseInt(userId));
            req.setAttribute("user", user);
            req.getRequestDispatcher("page/setting/SettingPage.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("errorUpdateName", e.getMessage());
        }
    }

    private void handlePostUpdatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                if (oldPassword.equals(newPassword)) {
                    req.setAttribute("errorNewPassword", "New password must be different from old password");
                    req.getRequestDispatcher("page/setting/SettingPage.jsp").forward(req, resp);
                    return;
                }
                req.setAttribute("successUpdatePassword", "Update password successfully");
            } else {
                req.setAttribute("errorUpdatePassword", "Update password failed");
            }

            RenderUserDto user = UserService.getInstance().getUserById(Integer.parseInt(userId));
            req.setAttribute("user", user);
            req.getRequestDispatcher("page/setting/SettingPage.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("errorUpdatePassword", e.getMessage());
            req.getRequestDispatcher("page/setting/SettingPage.jsp").forward(req, resp);
        }
    }

    private void handlePostUpdateAvatar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getAttribute("userId").toString() == null ? "0" : req.getAttribute("userId").toString();
        Part filePart = req.getPart("avatar") != null ? req.getPart("avatar") : null;
        if (filePart == null) {
            req.setAttribute("errorUpdateAvatar", "Avatar is not valid");
            req.getRequestDispatcher("page/setting/SettingPage.jsp").forward(req, resp);
            return;
        }
        String profilePicture = filePart.getSubmittedFileName();
        String extension = profilePicture.substring(profilePicture.lastIndexOf(".") + 1);
        String allowedExtension = "jpg,jpeg,png";
        if (!allowedExtension.contains(extension)) {
            req.setAttribute("errorUpdateAvatar", "Avatar is not valid");
            req.getRequestDispatcher("page/setting/SettingPage.jsp").forward(req, resp);
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
                req.setAttribute("successUpdateAvatar", "Update avatar successfully");
            } else {
                req.setAttribute("errorUpdateAvatar", "Update avatar failed");
            }

            RenderUserDto user = UserService.getInstance().getUserById(Integer.parseInt(userId));
            req.setAttribute("user", user);
            req.getRequestDispatcher("page/setting/SettingPage.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.setAttribute("errorUpdateAvatar", e.getMessage());
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
