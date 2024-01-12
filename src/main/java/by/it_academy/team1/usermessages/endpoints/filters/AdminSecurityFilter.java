package by.it_academy.team1.usermessages.endpoints.filters;

import by.it_academy.team1.usermessages.core.dto.UserLoginDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/template/ui/admin/*","/api/admin/*"})
public class AdminSecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();

        if ((session != null) && (session.getAttribute("user") != null)) {

            UserLoginDto user = (UserLoginDto) session.getAttribute("user");

            if (user.getUsername().equals("Admin")) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(contextPath + "/");
            }

        } else {
            resp.sendRedirect(contextPath + "/template/ui/signIn");
        }
    }
}
