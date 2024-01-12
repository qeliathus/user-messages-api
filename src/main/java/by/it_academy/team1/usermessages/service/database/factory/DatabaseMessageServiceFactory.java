package by.it_academy.team1.usermessages.service.database.factory;

import by.it_academy.team1.usermessages.dao.database.factory.DatabaseMessageDaoFactory;
import by.it_academy.team1.usermessages.service.api.IMessageService;
import by.it_academy.team1.usermessages.service.database.DatabaseMessageService;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

public class DatabaseMessageServiceFactory {

    private volatile static DatabaseMessageService instance;

    private DatabaseMessageServiceFactory() {
    }

    public static IMessageService getInstance() throws PropertyVetoException, SQLException, IOException {
        if(instance == null){
            synchronized (DatabaseMessageServiceFactory.class){
                if(instance == null){
                    instance = new DatabaseMessageService(DatabaseMessageDaoFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
