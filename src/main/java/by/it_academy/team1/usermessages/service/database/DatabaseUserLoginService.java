package by.it_academy.team1.usermessages.service.database;

import by.it_academy.team1.usermessages.core.dto.UserLoginDto;
import by.it_academy.team1.usermessages.core.entity.User;
import by.it_academy.team1.usermessages.core.exceptions.UserNotFoundException;
import by.it_academy.team1.usermessages.dao.api.IUserDao;
import by.it_academy.team1.usermessages.service.api.IUserLoginService;

public class DatabaseUserLoginService implements IUserLoginService {
    private IUserDao userDao;

    public DatabaseUserLoginService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void authenticate(UserLoginDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Нет информации о пользователе");
        }
        if (dto.getUsername() == null) {
            throw new IllegalArgumentException("Нет информации о логине");
        } else if (dto.getPassword() == null) {
            throw new IllegalArgumentException("Нет информации о пароле");
        }

        User user = userDao.findUser(dto.getUsername());
        if (user == null) {
            throw new UserNotFoundException("Не существет такого пользователя");
        }
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new UserNotFoundException("Пароль был неверно введен");
        }


    }
}
