package com.epam.expositions.servlet;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("")
public class TestServlet extends HttpServlet {

    public static String errorMessage = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("test", "1");
        req.setAttribute("message", errorMessage);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

}
