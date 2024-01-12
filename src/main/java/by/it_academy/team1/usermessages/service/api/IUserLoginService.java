package by.it_academy.team1.usermessages.service.api;

import by.it_academy.team1.usermessages.core.dto.UserLoginDto;

public interface IUserLoginService {

    void authenticate(UserLoginDto dto);
}
