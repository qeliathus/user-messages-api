package by.it_academy.team1.usermessages.dao.database;

import by.it_academy.team1.usermessages.core.entity.User;
import by.it_academy.team1.usermessages.core.entity.UserRole;
import by.it_academy.team1.usermessages.dao.api.IUserDao;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUserDao implements IUserDao {


    @Override
    public Boolean existsByUsername(String username) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/team_1","postgres", System.getenv("PGPASSWORD"))) {
            String savePositionSql = "SELECT exists (SELECT 1 FROM user_messages.users WHERE username = ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(savePositionSql)) {
                preparedStatement.setString(1, username);
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                resultSet.next();
                return resultSet.getBoolean(1);
            }
        } catch (SQLException exception) {
            System.out.println("SQL Exception " + exception.getErrorCode());
            System.out.println(Arrays.toString(exception.getStackTrace()));
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void saveNewUser(User user) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/team_1","postgres", System.getenv("PGPASSWORD"))) {
            String savePositionSql = "INSERT INTO user_messages.users" +
                    "(id, username, password, full_name, birthday, registration_date, role)" +
                    "VALUES (gen_random_uuid (),?,?,?,?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(savePositionSql)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getFullName());
                preparedStatement.setObject(4, user.getBirthday());
                preparedStatement.setObject(5, user.getRegisteredDate());
                preparedStatement.setString(6, user.getRole().toString());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException exception) {
            System.out.println("SQL Exception " + exception.getErrorCode());
            System.out.println(exception.getStackTrace().toString());
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Map<String, User> getRegistrationUsers() {
        Map<String, User> registrationUsers= new HashMap();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/team_1","postgres", System.getenv("PGPASSWORD"))) {
            String savePositionSql = "SELECT id, username, password, full_name, birthday, registration_date, role " +
                    "FROM user_messages.users;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(savePositionSql)) {
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    registrationUsers.put(resultSet.getString(1),
                            new User(
                                        resultSet.getString(1),
                                        resultSet.getString(2),
                                        resultSet.getString(3),
                                        resultSet.getString(4),
                                        resultSet.getObject(5, LocalDate.class),
                                        resultSet.getObject(6, LocalDateTime.class),
                                        UserRole.valueOf(resultSet.getString(7))
                            )
                    );
                }
                return registrationUsers;
            }
        } catch (SQLException exception) {
            System.out.println("SQL Exception " + exception.getErrorCode());
            System.out.println(exception.getStackTrace().toString());
            throw new RuntimeException(exception);
        }
    }

    @Override
    public User findUser(String username) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/team_1","postgres", System.getenv("PGPASSWORD"))) {
            String savePositionSql = "SELECT id, username, password, full_name, birthday, registration_date, role " +
                    "FROM user_messages.users " +
                    "WHERE username = ?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(savePositionSql)) {
                preparedStatement.setString(1, username);
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                resultSet.next();
                String uuid = resultSet.getString(1);
                String usernameDataBase = resultSet.getString(2);
                String password = resultSet.getString(3);
                String full_name = resultSet.getString(4);
                LocalDate birthday =  resultSet.getDate(5).toLocalDate();
                LocalDateTime registration_date = resultSet.getTimestamp(6).toLocalDateTime();
                UserRole role = UserRole.valueOf(resultSet.getString(7));

                User user = new User(uuid, usernameDataBase, password, full_name, birthday, registration_date, role);
                return user;
            }
        } catch (SQLException exception) {
            System.out.println("SQL Exception " + exception.getErrorCode());
            System.out.println(exception.getStackTrace().toString());
            throw new RuntimeException(exception);
        }
    }
}
