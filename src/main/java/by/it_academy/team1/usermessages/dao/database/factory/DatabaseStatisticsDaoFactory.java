package by.it_academy.team1.usermessages.dao.database.factory;

import by.it_academy.team1.usermessages.dao.api.IStatisticsDao;
import by.it_academy.team1.usermessages.dao.database.DatabaseStatisticsDao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

public class DatabaseStatisticsDaoFactory {
    private volatile static DatabaseStatisticsDao instance;

    private DatabaseStatisticsDaoFactory() {
    }

    public static IStatisticsDao getInstance() throws PropertyVetoException, SQLException, IOException {
        if(instance == null){
            synchronized (DatabaseStatisticsDaoFactory.class){
                if(instance == null){
                    instance = new DatabaseStatisticsDao();
                }
            }
        }
        return instance;
    }
}
