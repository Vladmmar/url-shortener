package com.vladm.servlet;

import com.vladm.JspHelper;
import com.vladm.dto.CreateUserDto;
import com.vladm.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("/register"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userDto = CreateUserDto.builder()
                .firstName(req.getParameter("first-name"))
                .lastName(req.getParameter("last-name"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .confirmationPassword(req.getParameter("confirm-password"))
                .profileImage(req.getPart("profile-image"))
                .birthday(req.getParameter("birthday"))
                .build();

        userService.create(userDto);
        req.getSession().setAttribute("user", userDto);
        req.getRequestDispatcher(JspHelper.getPath("cabinet"))
                .forward(req, resp);
    }
}
