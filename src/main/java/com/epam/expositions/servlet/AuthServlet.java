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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginDTO loginDTO = new LoginDTO(req.getParameter("username"),
                req.getParameter("password"));

        try {
            authService.authenticate(loginDTO);
        }
        catch (InvalidDataException exception){
            resp.sendError(422, exception.getMessage());
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("login", loginDTO.getLogin());

        resp.sendRedirect("/cabinet");
    }
}
