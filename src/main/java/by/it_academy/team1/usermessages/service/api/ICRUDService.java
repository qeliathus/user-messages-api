package by.it_academy.team1.usermessages.service.api;

import java.sql.SQLException;
import java.util.List;

public interface ICRUDService<T> {
    List<T> get() throws SQLException;

}
