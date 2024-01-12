package by.it_academy.team1.usermessages.endpoints.html.memory;

import by.it_academy.team1.usermessages.core.dto.UserLoginDto;
import by.it_academy.team1.usermessages.core.entity.Message;
import by.it_academy.team1.usermessages.core.exceptions.UserNotFoundException;
import by.it_academy.team1.usermessages.service.api.IMessageService;
import by.it_academy.team1.usermessages.service.memory.factory.MemoryMessageServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/ui/user/chats")
public class MemoryChatsServlet extends HttpServlet {

    public final IMessageService messageService;

    public MemoryChatsServlet(){ this.messageService = MemoryMessageServiceFactory.getInstance();}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try{
            UserLoginDto currentUser = (UserLoginDto) session.getAttribute("user");

            String username = currentUser.getUsername();
            List<Message> messages = this.messageService.getMessagesOfUser(username);
            req.setAttribute("username", username);

            req.setAttribute("chat", messages);
            req.getRequestDispatcher("/template/ui/user/chats/").forward(req, resp);
        } catch (UserNotFoundException | SQLException e){
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write(e.getMessage());
        }
    }
}
