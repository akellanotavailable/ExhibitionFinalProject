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
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    ExpositionService service = new ExpositionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ExpositionDTO> expositionList = service.findByDate(LocalDateTime.now());

        req.setAttribute("expositionList", expositionList);
        req.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(req, resp);
    }

}
