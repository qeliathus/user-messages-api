package by.it_academy.team1.usermessages.dao.api;

import by.it_academy.team1.usermessages.core.entity.Message;

import java.sql.SQLException;
import java.util.List;

public interface IMessageDao {

    List<Message> get() throws SQLException;

    void save(Message message);

}
