package com.epam.expositions.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/cabinet", "/purchase", "/history"})
public class LoginFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        String loginURI = request.getContextPath() + "/login";

        boolean loggedIn = (session != null) && (session.getAttribute("login") != null);

        if (loggedIn) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Login filter started");
    }

    @Override
    public void destroy() {
        System.out.println("Login filter stopped");
    }
}
