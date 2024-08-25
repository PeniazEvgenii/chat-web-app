package by.it_academy.jd2.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static by.it_academy.jd2.util.PathUtil.*;

@WebFilter(urlPatterns = {MESSAGE_SERVLET, PATTERN_URL_USER_UI})
public class UserSecurityFilter implements Filter {
    private static final String SESSION_ATTRIBUTE_USER = "user";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if(session != null && session.getAttribute(SESSION_ATTRIBUTE_USER) != null) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + LOGIN_SERVLET);
        }
    }
}
