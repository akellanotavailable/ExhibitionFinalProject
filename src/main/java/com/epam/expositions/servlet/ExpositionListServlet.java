package com.epam.expositions.servlet;

import com.epam.expositions.dto.ExpositionDTO;
import com.epam.expositions.entity.Exposition;
import com.epam.expositions.entity.Status;
import com.epam.expositions.entity.User;
import com.epam.expositions.service.ExpositionService;
import com.epam.expositions.service.UserService;
import com.epam.expositions.service.impl.ExpositionServiceImpl;
import com.epam.expositions.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/expositionlist")
public class ExpositionListServlet extends HttpServlet {
    ExpositionService expositionService = new ExpositionServiceImpl();
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = userService.findByLogin((String) req.getSession(false).getAttribute("login"));
        List<ExpositionDTO> expositionDTOS = expositionService.findByHostId(user.getId());

        req.setAttribute("expositionList", expositionDTOS);

        req.getRequestDispatcher("WEB-INF/jsp/expolist.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Exposition e = expositionService.findById(Long.parseLong(req.getParameter("expositionid")));
        e.setStatusName(Status.REFUNDED.getName());
        expositionService.update(e, e.getId());
        resp.sendRedirect("/expositionlist");
    }
}
