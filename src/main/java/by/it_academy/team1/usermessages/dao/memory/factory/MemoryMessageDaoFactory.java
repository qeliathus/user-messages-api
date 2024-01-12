package by.it_academy.team1.usermessages.dao.memory.factory;

import by.it_academy.team1.usermessages.dao.memory.MemoryMessageDao;
import by.it_academy.team1.usermessages.dao.api.IMessageDao;

public class MemoryMessageDaoFactory {
    private volatile static MemoryMessageDao instance;

    private MemoryMessageDaoFactory() {
    }

    public static IMessageDao getInstance() {
        if(instance == null){
            synchronized (MemoryMessageDaoFactory.class){
                if(instance == null){
                    instance = new MemoryMessageDao();
                }
            }
        }
        return instance;
    }
}
