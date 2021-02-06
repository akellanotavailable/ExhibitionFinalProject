package com.epam.expositions.servlet;

import com.epam.expositions.dto.RegisterDTO;
import com.epam.expositions.entity.Role;
import com.epam.expositions.entity.RoleName;
import com.epam.expositions.entity.User;
import com.epam.expositions.exception.InvalidDataException;
import com.epam.expositions.service.RegisterService;
import com.epam.expositions.service.UserService;
import com.epam.expositions.service.impl.RegisterServiceImpl;
import com.epam.expositions.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    RegisterService registerService = new RegisterServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegisterDTO registerDTO = RegisterDTO.builder()
                .login(req.getParameter("login"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .rePassword(req.getParameter("rePassword"))
                .build();

        try {
            registerService.register(registerDTO);

        } catch (InvalidDataException exception) {
            resp.sendError(422, exception.getMessage());
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("login", registerDTO.getLogin());

        resp.sendRedirect("/cabinet");

    }
}
