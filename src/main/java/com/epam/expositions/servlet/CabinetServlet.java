package com.epam.expositions.servlet;

import com.epam.expositions.entity.User;
import com.epam.expositions.service.UserService;
import com.epam.expositions.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cabinet")
public class CabinetServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) req.getSession().getAttribute("login");
        req.setAttribute("login", login);

        User user = userService.findByLogin(login);
        req.setAttribute("role", user.getRole().getName());

        if (user.getRole().getName().equals("admin")) {
            req.setAttribute("userList", userService.findALL());
        }
        req.setAttribute("userData", userService.findByLogin(login));

        req.getRequestDispatcher("WEB-INF/jsp/cabinet.jsp").forward(req, resp);
    }

}
