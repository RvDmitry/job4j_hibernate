package ru.job4j.manytomany.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.manytomany.model.City;
import ru.job4j.manytomany.model.Human;
import ru.job4j.manytomany.store.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Class HumanServlet
 *
 * @author Dmitry Razumov
 * @version 1
 */
@WebServlet("/humans")
public class HumanServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(HumanServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        List<City> cities = HbmStore.instanceOf().allCities();
        LOG.info("Получены города {}", cities);
        req.setAttribute("allCities", cities);
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        String[] cIds = req.getParameterValues("cIds");
        String info = req.getParameter("info");
        Human human = Human.of(info);
        LOG.info("Получена информация о человеке: {}", human);
        LOG.info("Получены идентификаторы городов: {}",
                Arrays.stream(cIds).reduce((x, y) -> x + " " + y));
        HbmStore.instanceOf().addNewHuman(human, cIds);
        resp.sendRedirect(req.getContextPath() + "/humans");
    }
}
