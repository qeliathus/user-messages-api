package by.it_academy.team1.usermessages.dao.api;

import java.sql.SQLException;

public interface IStatisticsDao  {
    int getUsersCount() throws SQLException;
    int getMessagesCount() throws SQLException;
}
