package com.epam.expositions.servlet;

import com.epam.expositions.entity.Purchase;
import com.epam.expositions.service.ExpositionService;
import com.epam.expositions.service.PurchaseService;
import com.epam.expositions.service.UserService;
import com.epam.expositions.service.impl.ExpositionServiceImpl;
import com.epam.expositions.service.impl.PurchaseServiceImpl;
import com.epam.expositions.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    PurchaseService purchaseService = new PurchaseServiceImpl();
    ExpositionService expositionService = new ExpositionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String login = session.getAttribute("login").toString();
        Long userId = userService.findByLogin(login).getId();
        List<Purchase> purchaseList = purchaseService.findByUserId(userId);
        HashMap<String, String> purchaseMap = new HashMap<>();
        for (Purchase purchase:
             purchaseList) {
            purchaseMap.put(expositionService.findById(purchase.getExpositionId()).getTopic(),
                    purchase.getStatus().getName());
        }

        req.setAttribute("purchaseMap",purchaseMap.entrySet());

        req.getRequestDispatcher("WEB-INF/jsp/history.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
