package com.example.mysterybook.controllers.user;

import com.example.mysterybook.dto.user.LoginUserDto;
import com.example.mysterybook.models.User;
import com.example.mysterybook.services.user.UserService;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("LoginController.init");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginController.doGet");
        request.getRequestDispatcher("page/login/LoginPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginController.doPost");
        LoginUserDto dto = new LoginUserDto();
        dto.setUsername(request.getParameter("username"));
        dto.setPassword(request.getParameter("password"));
        try {
            User loginResult = UserService.getInstance().loginUser(dto);
            if (loginResult == null) {
                request.setAttribute("error", "Wrong username or password");
                request.getRequestDispatcher("page/login/LoginPage.jsp").forward(request, response);
            } else {
                Cookie cookie = new Cookie("userId", loginResult.getId() + "");
                response.addCookie(cookie);
                response.sendRedirect("/home");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {
        System.out.println("LoginController.destroy");
        super.destroy();
    }
}
