package com.epam.expositions.servlet;

import com.epam.expositions.service.UserService;
import com.epam.expositions.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userlist")
public class UserListServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userList", userService.findALL());
        req.getRequestDispatcher("jsp/userlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!userService.deleteById(Long.parseLong(req.getParameter("userid")))) {
            System.out.println("user with id " + req.getParameter("userid") + " was not deleted");
        }
        resp.sendRedirect("/userlist");
    }

}
