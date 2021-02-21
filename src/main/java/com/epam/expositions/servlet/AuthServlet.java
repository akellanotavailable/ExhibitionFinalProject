package com.epam.expositions.servlet;

import com.epam.expositions.dto.LoginDTO;
import com.epam.expositions.exception.InvalidDataException;
import com.epam.expositions.service.AuthService;
import com.epam.expositions.service.impl.AuthServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {

    private final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("login") != null) {
            resp.sendRedirect("/cabinet");
        } else
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession(false) != null) {
            req.getSession(false).invalidate();
        }

        LoginDTO loginDTO = new LoginDTO(req.getParameter("username"),
                req.getParameter("password"));

        try {
            authService.authenticate(loginDTO);
        } catch (InvalidDataException exception) {
            resp.sendError(422, exception.getMessage());
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("login", loginDTO.getLogin());

        resp.sendRedirect("/cabinet");
    }

}
//todo: Common servlet overrides method "service" to catch exceptions