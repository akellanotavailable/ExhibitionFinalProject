package com.epam.expositions.servlet;

import com.epam.expositions.entity.Purchase;
import com.epam.expositions.entity.User;
import com.epam.expositions.service.PurchaseService;
import com.epam.expositions.service.UserService;
import com.epam.expositions.service.impl.PurchaseServiceImpl;
import com.epam.expositions.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/expositionstats")
public class ExpositionStatisticsServlet extends HttpServlet {
    PurchaseService purchaseService = new PurchaseServiceImpl();
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String expositionId = req.getParameter("expositionid");
        List<Purchase> purchaseList = purchaseService.findByExpositionId(Long.parseLong(expositionId));
        List<User> userList = purchaseList.stream()
                .map(purchase -> userService.findById(purchase.getUserId()))
                .collect(Collectors.toList());
        req.setAttribute("userList", userList);
        req.getRequestDispatcher("WEB-INF/jsp/expostats.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (purchaseService.deleteByUserId(Long.parseLong(req.getParameter("userId")))) {
            resp.sendRedirect("/expositionstats");
        } else {
            System.out.println("Cannot delete user.");
        }

    }
}
