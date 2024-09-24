package by.it_academy.jd2.controller.filter;

import by.it_academy.jd2.dto.UserReadDto;
import by.it_academy.jd2.entity.ERole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static by.it_academy.jd2.util.PathUtil.*;

@WebFilter(urlPatterns = {PATTERN_URL_ADMIN_API, PATTERN_URL_ADMIN_UI})
public class AdminSecurrityFilter implements Filter {
    private final String adminRole = ERole.ADMIN.name();
    private static final String SESSION_ATTRIBUTE_USER = "user";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        if(checkAdminPrivileges(req)) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect(req.getContextPath() + LOGIN_SERVLET);
           // ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }


    private boolean checkAdminPrivileges(HttpServletRequest req) {
        HttpSession session = req.getSession();

        if(session != null) {
            UserReadDto user = (UserReadDto) session.getAttribute(SESSION_ATTRIBUTE_USER);
            if(user != null) {
               return user.getRole().name().equalsIgnoreCase(adminRole);
            }
        }

        return false;
    }
}
