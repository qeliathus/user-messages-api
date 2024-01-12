package by.it_academy.team1.usermessages.endpoints.html.database;

import by.it_academy.team1.usermessages.core.dto.MessageDto;
import by.it_academy.team1.usermessages.core.dto.UserLoginDto;
import by.it_academy.team1.usermessages.core.entity.User;
import by.it_academy.team1.usermessages.core.exceptions.UserNotFoundException;
import by.it_academy.team1.usermessages.dao.api.IUserDao;
import by.it_academy.team1.usermessages.dao.database.DatabaseUserDao;
import by.it_academy.team1.usermessages.dao.memory.MemoryUserDao;
import by.it_academy.team1.usermessages.service.api.IMessageService;
import by.it_academy.team1.usermessages.service.database.factory.DatabaseMessageServiceFactory;
import by.it_academy.team1.usermessages.service.memory.factory.MemoryMessageServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/database/api/message")
public class DatabaseMessageServlet extends HttpServlet {
    private IMessageService messageService = DatabaseMessageServiceFactory.getInstance();
    private static final String RECIPIENT_PARAM_NAME = "recipient";
    private static final String MESSAGE_TEXT_PARAM_NAME = "text";

    public DatabaseMessageServlet() throws PropertyVetoException, SQLException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        HttpSession session = req.getSession();

        try{

            UserLoginDto currentUser = (UserLoginDto) session.getAttribute("user");

            // Получите параметры из POST-запроса
            String recipient = req.getParameter(RECIPIENT_PARAM_NAME);
            String messageText = req.getParameter(MESSAGE_TEXT_PARAM_NAME);
            IUserDao check = new DatabaseUserDao();
            // Проверка, зарегистрирован ли получатель
            User recipientUser = check.findUser(recipient);

            if (recipientUser == null) {
                // Пользователь не зарегистрирован
                req.setAttribute("error", true);
                req.setAttribute("message", "Получатель не зарегистрирован");
                req.getRequestDispatcher("/template/database/user/message/").forward(req, resp);
                return;
            }

            // Создайте новое сообщение
            MessageDto message = new MessageDto();
            message.setUsernameFrom(currentUser.getUsername()); // Установите отправителя как текущего пользователя
            message.setUsernameTo(recipient);
            message.setText(messageText);


            try{
                this.messageService.sendMessage(message);
                req.setAttribute("success", true);
            } catch (IllegalArgumentException e){
                req.setAttribute("error", true);
                req.setAttribute("message",  e.getMessage());
            }
            req.getRequestDispatcher("/template/database/user/message/").forward(req, resp);

            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (UserNotFoundException e){
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write(e.getMessage());
        }

    }
}

