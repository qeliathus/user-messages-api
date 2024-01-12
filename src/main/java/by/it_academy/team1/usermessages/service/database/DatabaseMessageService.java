package by.it_academy.team1.usermessages.service.database;

import by.it_academy.team1.usermessages.core.dto.MessageDto;
import by.it_academy.team1.usermessages.core.entity.Message;
import by.it_academy.team1.usermessages.dao.api.IMessageDao;
import by.it_academy.team1.usermessages.service.api.IMessageService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseMessageService implements IMessageService {

    IMessageDao messageDao;
    public DatabaseMessageService(IMessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public List<Message> getMessagesOfUser(String username) throws SQLException {
        return this.messageDao.get().stream()
                .filter(message -> message.getUsernameTo().equals(username))
                .collect(Collectors.toList());
    }

    @Override
    public void sendMessage(MessageDto message) {
        Message entity = new Message();
        entity.setUsernameTo(message.getUsernameTo());
        entity.setUsernameFrom(message.getUsernameFrom());
        entity.setText(message.getText());
        entity.setSentDate(LocalDateTime.now()); // Установите дату/время отправки

        this.messageDao.save(entity);
    }

    @Override
    public List<Message> get() throws SQLException {
        return this.messageDao.get();
    }
}
