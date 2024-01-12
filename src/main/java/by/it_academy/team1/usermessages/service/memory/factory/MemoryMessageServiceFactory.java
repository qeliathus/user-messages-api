package by.it_academy.team1.usermessages.service.memory.factory;

import by.it_academy.team1.usermessages.dao.memory.factory.MemoryMessageDaoFactory;
import by.it_academy.team1.usermessages.service.memory.MemoryMessageService;
import by.it_academy.team1.usermessages.service.api.IMessageService;

public class MemoryMessageServiceFactory {
    private volatile static MemoryMessageService instance;

    private MemoryMessageServiceFactory() {
    }

    public static IMessageService getInstance() {
        if(instance == null){
            synchronized (MemoryMessageServiceFactory.class){
                if(instance == null){
                    instance = new MemoryMessageService(MemoryMessageDaoFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
