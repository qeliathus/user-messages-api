package by.it_academy.team1.usermessages.dao.memory.factory;

import by.it_academy.team1.usermessages.dao.memory.MemoryUserDao;
import by.it_academy.team1.usermessages.dao.api.IUserDao;

public class MemoryUserDaoFactory {
    private volatile static MemoryUserDao instance;

    private MemoryUserDaoFactory() {
    }

    public static IUserDao getInstance() {
        if(instance == null){
            synchronized (MemoryUserDaoFactory.class){
                if(instance == null){
                    instance = new MemoryUserDao();
                }
            }
        }
        return instance;
    }
}
