package by.it_academy.team1.usermessages.service.api;

import by.it_academy.team1.usermessages.core.dto.UserRegistrationDto;

public interface IUserService {

    void saveNewUser(UserRegistrationDto dto);

}
