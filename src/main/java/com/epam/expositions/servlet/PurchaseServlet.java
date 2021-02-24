package com.epam.expositions.servlet;

import com.epam.expositions.dao.ExpositionDAO;
import com.epam.expositions.entity.Exposition;
import com.epam.expositions.entity.Purchase;
import com.epam.expositions.entity.Status;
import com.epam.expositions.entity.User;
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
import java.io.IOException;
import java.util.List;

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {
    ExpositionService expositionService = new ExpositionServiceImpl();
    UserService userService = new UserServiceImpl();
    PurchaseService purchaseService = new PurchaseServiceImpl();
    User user;
    Exposition exposition;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        Long expositionId = Long.parseLong(req.getParameter("expositionId"));

        user = userService.findByLogin(username);
        exposition = expositionService.findById(expositionId);

        List<Purchase> purchases = purchaseService.findByUserId(user.getId());
        if (purchases.stream().anyMatch(purchase -> purchase.getExpositionId().equals(exposition.getId()))) {
            resp.sendError(423, "You already have this ticket.");
            return;
        }
        if (exposition.getCapacity() == 0){
            resp.sendError(423, "Sold out.");
            return;
        }

        req.setAttribute("username", username);
        req.setAttribute("exposition", exposition);

        req.getRequestDispatcher("WEB-INF/jsp/purchase.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        purchaseService.create(new Purchase(exposition.getId(),user.getId(), Status.PURCHASED));
        exposition.setCapacity(exposition.getCapacity()-1);
        expositionService.update(exposition,exposition.getId());
        resp.sendRedirect("/thanks");
    }
}
