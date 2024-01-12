package by.it_academy.team1.usermessages.service.database.factory;

import by.it_academy.team1.usermessages.dao.database.factory.DatabaseUserDaoFactory;
import by.it_academy.team1.usermessages.service.api.IUserLoginService;
import by.it_academy.team1.usermessages.service.database.DatabaseUserLoginService;

public class DatabaseUserLoginServiceFactory {
    private volatile static DatabaseUserLoginService instance;

    private DatabaseUserLoginServiceFactory() {
    }

    public static IUserLoginService getInstance() {
        if(instance == null){
            synchronized (DatabaseUserLoginServiceFactory.class){
                if(instance == null){
                    instance = new DatabaseUserLoginService(DatabaseUserDaoFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
