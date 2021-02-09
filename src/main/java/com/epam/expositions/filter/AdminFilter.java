package com.epam.expositions.filter;

import com.epam.expositions.dao.UserDAO;
import com.epam.expositions.dao.impl.UserDAOImpl;
import com.epam.expositions.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;


@WebFilter("/userlist")
public class AdminFilter extends HttpFilter {
    private static final UserDAO userService = new UserDAOImpl();

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        boolean loggedIn = (session != null) && (session.getAttribute("login") != null);
        boolean isAdmin = false;

        User user;
        if(loggedIn){
        if (userService.findByLogin(session.getAttribute("login").toString()).isPresent()) {
            user = userService.findByLogin(session.getAttribute("login").toString()).get();
            if (user.getRole().getName().equals("admin")) {
                isAdmin = true;
            }
        }}

        if (loggedIn && isAdmin) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(403, "You have no access to this page.");
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Admin filter started");
    }

    @Override
    public void destroy() {
        System.out.println("Admin filter stopped");
    }
}