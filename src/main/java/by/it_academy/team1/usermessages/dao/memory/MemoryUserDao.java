package by.it_academy.team1.usermessages.dao.memory;

import by.it_academy.team1.usermessages.core.entity.User;
import by.it_academy.team1.usermessages.core.entity.UserRole;
import by.it_academy.team1.usermessages.dao.api.IUserDao;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MemoryUserDao implements IUserDao {

    private static Map<String, User> registrationUsers = new HashMap<>();

    static {
        User admin = createAdmin();
        registrationUsers.put(admin.getUuid(), admin);
    }

    private static User createAdmin() {
        User admin = new User();
        admin.setUsername("Admin");
        admin.setPassword("Admin");
        admin.setFullName("Admin A.A.");
        admin.setBirthday(LocalDate.of(2000,01,01));
        admin.setRole(UserRole.ADMIN);
        admin.setRegisteredDate(LocalDateTime.now());
        return admin;
    }

    @Override
    public Map<String, User> getRegistrationUsers() {
        return registrationUsers;
    }


    @Override
    public void saveNewUser(User user) {
        registrationUsers.put(user.getUuid(), user);
    }

    @Override
    public User findUser(String username) {
        for (Map.Entry<String, User> entry : registrationUsers.entrySet()) {
            if (entry.getValue().getUsername().equals(username)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return registrationUsers
                .entrySet()
                .stream()
                .anyMatch(it -> username.equals(it.getValue().getUsername()));
    }
}
