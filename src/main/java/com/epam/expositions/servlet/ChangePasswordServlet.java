package com.epam.expositions.servlet;

import com.epam.expositions.entity.User;
import com.epam.expositions.service.UserService;
import com.epam.expositions.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/changepassword")
public class ChangePasswordServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        User user = userService.findByLogin(String.valueOf(session.getAttribute("login")));

        String oldPassword = req.getParameter("password");
        String newPassword = req.getParameter("newPassword");
        String reNewPassword = req.getParameter("newRePassword");

        if(!oldPassword.equals(user.getPassword())){
            resp.sendError(422, "Incorrect password.");
            return;
        }

        if(!newPassword.equals(reNewPassword)){
            resp.sendError(422, "Passwords do not match.");
            return;
        }

        user.setPassword(newPassword);

        userService.update(user, user.getId());

        resp.sendRedirect("/cabinet");
    }
}
