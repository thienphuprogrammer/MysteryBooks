package com.example.mysterybook.controllers.user;

import com.example.mysterybook.dto.user.RegisterUserDto;
import com.example.mysterybook.errors.ValidationError;
import com.example.mysterybook.services.user.UserService;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.List;

@WebServlet(name = "RegisterController", urlPatterns = "/register")
public class RegisterController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("page/register/RegisterPage.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RegisterUserDto dto = new RegisterUserDto();
            String username = request.getParameter("username") == null ? "" : request.getParameter("username");
            username = username.trim();
            String email = request.getParameter("email") == null ? "" : request.getParameter("email");
            email = email.trim();
            String phone = request.getParameter("phone") == null ? "" : request.getParameter("phone");
            phone = phone.trim();
            String password = request.getParameter("password") == null ? "" : request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String fullName = request.getParameter("fullName") == null ? "" : request.getParameter("fullName");
            fullName = fullName.trim();
            String address = request.getParameter("address") == null ? "" : request.getParameter("address");
            address = address.trim();

            dto.setUsername(username);
            dto.setEmail(email);
            dto.setPhoneNumber(phone);
            dto.setPassword(password);
            dto.setConfirmPassword(confirmPassword);
            dto.setFullName(fullName);
            dto.setAddress(address);

            List<ValidationError> errors = dto.validate();
            if (!errors.isEmpty()) {
                for (ValidationError error : errors) {
                    request.setAttribute(error.getName(), error.getMessage());
                }
                request.setAttribute("info", dto);
                request.getRequestDispatcher("page/register/RegisterPage.jsp").forward(request, response);
                return;
            }

            boolean createAccountResult = UserService.getInstance().registerUser(dto);
            if (createAccountResult) {
                request.setAttribute("success", "Register successfully");
                response.sendRedirect("/login");
                return;
            } else {
                request.setAttribute("info", dto);
                request.setAttribute("error", "Register failed");
            }
            request.getRequestDispatcher("page/register/RegisterPage.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Register failed");
            System.out.println(e.getMessage());
        }
    }
}
