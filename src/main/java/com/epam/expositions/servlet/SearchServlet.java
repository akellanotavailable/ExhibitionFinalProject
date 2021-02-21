package com.epam.expositions.servlet;

import com.epam.expositions.dto.ExpositionDTO;
import com.epam.expositions.service.ExpositionService;
import com.epam.expositions.service.impl.ExpositionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    ExpositionService expositionService = new ExpositionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ExpositionDTO> expositionList = expositionService.findALL(false);

        String search = req.getParameter("search");
        String order = req.getParameter("order");

        req.setAttribute("search", search);

        List<ExpositionDTO> expositionSearch =
                expositionList.stream()
                        .filter(exposition -> exposition.getTopic().toLowerCase(Locale.ROOT)
                                .contains(search.toLowerCase(Locale.ROOT)))
                        .collect(Collectors.toList());

        expositionSearch = expositionSearch.stream()
                .sorted((o1, o2) -> {
                    if (order != null) {
                        switch (order) {
                            case "date_startDESC":
                                return o1.getDateStart().compareToIgnoreCase(o2.getDateStart());
                            case "date_startASC":
                                return o2.getDateStart().compareToIgnoreCase(o1.getDateStart());
                            case "topicDESC":
                                return o1.getTopic().compareToIgnoreCase(o2.getTopic());
                            case "topicASC":
                                return o2.getTopic().compareToIgnoreCase(o1.getTopic());
                            case "priceDESC":
                                return o1.getPrice().compareTo(o2.getPrice());
                            case "priceASC":
                                return o2.getPrice().compareTo(o1.getPrice());
                        }
                    }
                    return 0;
                })
                .collect(Collectors.toList());

        req.setAttribute("expositionList", expositionSearch);
        req.getRequestDispatcher("WEB-INF/jsp/search.jsp").forward(req, resp);
    }


}
