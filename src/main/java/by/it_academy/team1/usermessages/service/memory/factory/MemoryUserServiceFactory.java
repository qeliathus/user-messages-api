package by.it_academy.team1.usermessages.service.memory.factory;

import by.it_academy.team1.usermessages.dao.memory.factory.MemoryUserDaoFactory;
import by.it_academy.team1.usermessages.service.memory.MemoryUserService;
import by.it_academy.team1.usermessages.service.api.IUserService;

public class MemoryUserServiceFactory {

    private volatile static MemoryUserService instance;

    private MemoryUserServiceFactory() {
    }

    public static IUserService getInstance() {
        if(instance == null){
            synchronized (MemoryUserServiceFactory.class){
                if(instance == null){
                    instance = new MemoryUserService(MemoryUserDaoFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
