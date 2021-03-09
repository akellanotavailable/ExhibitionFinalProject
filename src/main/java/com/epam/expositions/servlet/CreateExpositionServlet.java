package com.epam.expositions.servlet;

import com.epam.expositions.dto.DurationDTO;
import com.epam.expositions.dto.HallTimetableDTO;
import com.epam.expositions.entity.*;
import com.epam.expositions.service.ExpositionService;
import com.epam.expositions.service.HallService;
import com.epam.expositions.service.UserService;
import com.epam.expositions.service.impl.ExpositionServiceImpl;
import com.epam.expositions.service.impl.HallServiceImpl;
import com.epam.expositions.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/newexposition")
public class CreateExpositionServlet extends HttpServlet {

    ExpositionService expositionService = new ExpositionServiceImpl();
    UserService userService = new UserServiceImpl();
    HallService hallService = new HallServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/newexpo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        LocalDateTime dateStart = LocalDateTime.parse(req.getParameter("dateStart"));
        LocalDateTime dateEnd = LocalDateTime.parse(req.getParameter("dateEnd"));
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        Long capacity = Long.parseLong(req.getParameter("capacity"));
        String imageLink = req.getParameter("imageLink");
        String detailsLink = req.getParameter("detailsLink");

        User user = userService.findByLogin(String.valueOf(req.getSession(false).getAttribute("login")));

        if (dateStart.isAfter(dateEnd)) {
            resp.sendError(422, "Start date is after end date.");
            return;
        }

        if (capacity <= 0) {
            resp.sendError(422, "Please enter valid number of tickets.");
            return;
        }

        if (price.doubleValue() < 0) {
            resp.sendError(422, "Please enter valid price.");
            return;
        }

        Exposition exposition = Exposition.builder()
                .topic(title)
                .hostId(user.getId())
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .price(price)
                .capacity(capacity)
                .imagePath(imageLink)
                .detailsLink(detailsLink)
                .statusName(Status.WAITING.getName())
                .build();

        expositionService.create(exposition);

        if (!user.getRole().getName().equals("client") || !user.getRole().getName().equals("admin")) {
            user.setRole(new Role(2L, "client"));
            userService.update(user, user.getId());
        }
        req.getSession(false).setAttribute("exposition", exposition);

        DurationDTO reservationTime = new DurationDTO(exposition.getDateStart(), exposition.getDateEnd());

        List<HallTimetableDTO> hallTimetableList = hallService.findAll();

        hallTimetableList = hallTimetableList.stream()
                .filter(hallTimetableDTO ->
                        hallTimetableDTO.getReservationDateTime().stream().allMatch(unavailableTime ->
                                //if start time is within reserved time
                                !(reservationTime.getDateStart().isAfter(unavailableTime.getDateStart()) &&
                                        reservationTime.getDateStart().isBefore(unavailableTime.getDateEnd()))
                                        &&
                                        //if end time is within reserved time
                                        !(reservationTime.getDateEnd().isBefore(unavailableTime.getDateEnd()) &&
                                                reservationTime.getDateEnd().isAfter(unavailableTime.getDateStart()))))
                .collect(Collectors.toList());

        List<Hall> hallList = hallTimetableList.stream()
                .map(HallTimetableDTO::getHall)
                .collect(Collectors.toList());

        req.getSession(false).setAttribute("hallList", hallList);

        req.getRequestDispatcher("WEB-INF/jsp/newexpohall.jsp").forward(req, resp);
    }
}
