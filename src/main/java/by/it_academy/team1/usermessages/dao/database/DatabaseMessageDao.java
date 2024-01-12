package by.it_academy.team1.usermessages.dao.database;

import by.it_academy.team1.usermessages.core.entity.Message;
import by.it_academy.team1.usermessages.dao.api.IMessageDao;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMessageDao implements IMessageDao {

    private DataSource ds = DataSourceCreator.getInstance();

    private List<Message> data;

    public DatabaseMessageDao() throws PropertyVetoException, SQLException, IOException {
    }

    @Override
    public List<Message> get() throws SQLException {
        data = new ArrayList<>();
        String sql = "SELECT sent_date, s1.username AS user_from , s2.username AS user_to, message_text " +
                "from user_messages.messages " +
                "INNER JOIN user_messages.users as s1 " +
                "ON user_messages.messages.user_id_from = s1.id " +
                "INNER JOIN user_messages.users as s2 " +
                "ON user_messages.messages.user_id_to = s2.id";
        try (Connection conn = ds.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()){
                data.add(new Message(resultSet.getTimestamp(1).toLocalDateTime(),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)));
            }
        }
        return data;

    }

    @Override
    public void save(Message message) {

        String sql = "INSERT INTO user_messages.messages " +
                "(id, user_id_from, user_id_to, sent_date, message_text) " +
                "VALUES (gen_random_uuid(), " +
                "(SELECT id FROM user_messages.users WHERE username = ?), " +
                "(SELECT id FROM user_messages.users WHERE username = ?), ?, ?)";
        try (Connection conn = ds.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, message.getUsernameFrom());
            stm.setString(2, message.getUsernameTo());
            stm.setObject(3, message.getSentDate());
            stm.setObject(4, message.getText());
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
