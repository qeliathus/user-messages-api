package by.it_academy.team1.usermessages.service.database;

import by.it_academy.team1.usermessages.dao.api.IStatisticsDao;
import by.it_academy.team1.usermessages.dao.database.factory.DatabaseStatisticsDaoFactory;
import by.it_academy.team1.usermessages.service.api.IStatisticsService;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseStatisticsService implements IStatisticsService {
    public static final String TOTAL_USERS_KEY = "total_registration_users";
    public static final String TOTAL_MESSAGES_KEY = "total_messages";
    public static final String TOTAL_ACTIVE_USERS_KEY = "total_active_sessions";
    private final static DatabaseStatisticsService instance;

    static {
        try {
            instance = new DatabaseStatisticsService();
        } catch (PropertyVetoException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final AtomicInteger sessionCounter;
    private final IStatisticsDao statisticsDao;

    public DatabaseStatisticsService() throws PropertyVetoException, SQLException, IOException {
        this.sessionCounter = new AtomicInteger(0);
        this.statisticsDao = DatabaseStatisticsDaoFactory.getInstance();
    }

    @Override
    public Map<String, Integer> getStats() throws SQLException {
        Map<String, Integer> stats = new HashMap<>();
        stats.put(TOTAL_USERS_KEY, statisticsDao.getUsersCount());
        stats.put(TOTAL_MESSAGES_KEY, statisticsDao.getMessagesCount());
        stats.put(TOTAL_ACTIVE_USERS_KEY, this.getSessionCount());
        return stats;
    }

    @Override
    public Integer incSessionCount() {
        return this.sessionCounter.incrementAndGet();
    }

    @Override
    public Integer decSessionCount() {
        return this.sessionCounter.decrementAndGet();
    }

    @Override
    public Integer getSessionCount() {
        return this.sessionCounter.get();
    }

    public static DatabaseStatisticsService getInstance() {
        return instance;
    }
}
