package by.it_academy.team1.usermessages.endpoints.html.database;

import by.it_academy.team1.usermessages.service.api.IStatisticsService;
import by.it_academy.team1.usermessages.service.database.DatabaseStatisticsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static by.it_academy.team1.usermessages.service.memory.MemoryStatisticsService.TOTAL_ACTIVE_USERS_KEY;
import static by.it_academy.team1.usermessages.service.memory.MemoryStatisticsService.TOTAL_MESSAGES_KEY;
import static by.it_academy.team1.usermessages.service.memory.MemoryStatisticsService.TOTAL_USERS_KEY;

@WebServlet(urlPatterns = "/database/api/admin/statistics")
public class DatabaseStatisticsServlet extends HttpServlet {
    private final IStatisticsService statisticsService = DatabaseStatisticsService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        try {
            Map<String, Integer> stats = statisticsService.getStats();
            req.setAttribute(TOTAL_USERS_KEY, stats.get(TOTAL_USERS_KEY));
            req.setAttribute(TOTAL_MESSAGES_KEY, stats.get(TOTAL_MESSAGES_KEY));
            req.setAttribute(TOTAL_ACTIVE_USERS_KEY, stats.get(TOTAL_ACTIVE_USERS_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("/template/database/admin/statistics/index.jsp").forward(req, resp);
    }
}
