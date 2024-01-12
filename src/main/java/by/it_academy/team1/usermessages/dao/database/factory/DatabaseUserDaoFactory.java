package by.it_academy.team1.usermessages.dao.database.factory;

import by.it_academy.team1.usermessages.dao.api.IUserDao;
import by.it_academy.team1.usermessages.dao.database.DatabaseUserDao;
import by.it_academy.team1.usermessages.dao.memory.MemoryUserDao;
import by.it_academy.team1.usermessages.dao.memory.factory.MemoryUserDaoFactory;

public class DatabaseUserDaoFactory {
    private volatile static DatabaseUserDao instance;

    private DatabaseUserDaoFactory() {
    }

    public static IUserDao getInstance() {
        if(instance == null){
            synchronized (DatabaseUserDaoFactory.class){
                if(instance == null){
                    instance = new DatabaseUserDao();
                }
            }
        }
        return instance;
    }
}
