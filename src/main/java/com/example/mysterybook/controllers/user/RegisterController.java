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
            dto.setUsername(request.getParameter("username"));
            dto.setEmail(request.getParameter("email"));
            dto.setPhoneNumber(request.getParameter("phone"));
            dto.setPassword(request.getParameter("password"));
            dto.setConfirmPassword(request.getParameter("confirmPassword"));
            dto.setFullName(request.getParameter("fullName"));
            dto.setAddress(request.getParameter("address"));

            List<ValidationError> errors = dto.validate();
            if (!errors.isEmpty()) {
                for (ValidationError error : errors) {
                    request.setAttribute(error.getName(), error.getMessage());
                }
                request.getRequestDispatcher("page/register/RegisterPage.jsp").forward(request, response);
            } else {
                boolean createAccountResult = UserService.getInstance().registerUser(dto);
                if (createAccountResult) {
                    request.setAttribute("success", "Register successfully");
                    response.sendRedirect("page/register/RegisterPage.jsp");
                } else {
                    request.setAttribute("error", "Register failed");
                    request.getRequestDispatcher("page/register/RegisterPage.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", "Register failed");
            System.out.println(e.getMessage());
        }
    }
}
