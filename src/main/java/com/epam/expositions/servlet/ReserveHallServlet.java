package com.epam.expositions.servlet;

import com.epam.expositions.entity.Exposition;
import com.epam.expositions.entity.Hall;
import com.epam.expositions.entity.Status;
import com.epam.expositions.service.ExpositionService;
import com.epam.expositions.service.HallService;
import com.epam.expositions.service.impl.ExpositionServiceImpl;
import com.epam.expositions.service.impl.HallServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@WebServlet("/reservehall")
public class ReserveHallServlet extends HttpServlet {

    HallService hallService = new HallServiceImpl();
    ExpositionService expositionService = new ExpositionServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        Exposition exposition = (Exposition) session.getAttribute("exposition");
        session.removeAttribute("exposition");

        List<Hall> hallList = (List<Hall>) session.getAttribute("hallList");
        session.removeAttribute("hallList");

        String[] names = req.getParameterValues("hallCheck");
        List<String> checked = Arrays.asList(names.clone());
        hallList.forEach(hall -> {
            if(checked.contains(String.valueOf(hall.getId()))){
                hallService.createHallReservation(hall.getId(), exposition.getId());
            }
        });

        exposition.setStatusName(Status.PURCHASED.getName());
        expositionService.update(exposition, exposition.getId());

        resp.sendRedirect("/thanks");
    }
}

