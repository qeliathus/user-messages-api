package by.it_academy.team1.usermessages.endpoints.html.database;

import by.it_academy.team1.usermessages.core.dto.UserRegistrationDto;
import by.it_academy.team1.usermessages.service.api.IUserService;
import by.it_academy.team1.usermessages.service.database.DatabaseUserService;
import by.it_academy.team1.usermessages.service.database.factory.DatabaseUserServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = "/database/api/user")
public class DatabaseUserServlet extends HttpServlet {
    private static final String USER_PARAM_USERNAME = "username";
    private static final String USER_PARAM_PASSWORD = "password";
    private static final String USER_PARAM_FULLNAME = "fullName";
    private static final String USER_PARAM_BIRTHDAY = "birthday";

    IUserService userService = DatabaseUserServiceFactory.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        String username = req.getParameter(USER_PARAM_USERNAME);
        String password = req.getParameter(USER_PARAM_PASSWORD);
        String fullName = req.getParameter(USER_PARAM_FULLNAME);
        String string_birthday = req.getParameter(USER_PARAM_BIRTHDAY);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthday = LocalDate.parse(string_birthday, formatter);

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setUsername(username);
        userRegistrationDto.setPassword(password);
        userRegistrationDto.setFullName(fullName);
        userRegistrationDto.setBirthday(birthday);

        userService.saveNewUser(userRegistrationDto);
        resp.sendRedirect("/user-messages-api/template/database/signIn");
        resp.setStatus(201);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        resp.sendRedirect("/user-messages-api/template/database/signUp");
    }
}
