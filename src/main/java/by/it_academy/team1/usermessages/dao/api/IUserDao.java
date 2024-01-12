package by.it_academy.team1.usermessages.dao.api;

import by.it_academy.team1.usermessages.core.entity.User;

import java.util.Map;

public interface IUserDao {
    void saveNewUser(User user);

    User findUser(String username);

    Boolean existsByUsername(String username);

    Map<String, User> getRegistrationUsers();
}
