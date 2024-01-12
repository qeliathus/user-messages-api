package by.it_academy.team1.usermessages.service.database.factory;

import by.it_academy.team1.usermessages.dao.database.factory.DatabaseUserDaoFactory;
import by.it_academy.team1.usermessages.service.api.IUserService;
import by.it_academy.team1.usermessages.service.database.DatabaseUserService;

public class DatabaseUserServiceFactory {

    private volatile static DatabaseUserService instance;

    private DatabaseUserServiceFactory() {
    }

    public static IUserService getInstance() {
        if(instance == null){
            synchronized (DatabaseUserServiceFactory.class){
                if(instance == null){
                    instance = new DatabaseUserService(DatabaseUserDaoFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
