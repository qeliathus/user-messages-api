package by.it_academy.team1.usermessages.service.api;

import java.sql.SQLException;
import java.util.Map;

public interface IStatisticsService {
    Map<String, Integer> getStats() throws SQLException;
    Integer incSessionCount();
    Integer decSessionCount();
    Integer getSessionCount();

}

