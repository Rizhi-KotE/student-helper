package sh.filter;

import sh.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sh.model.Role.ADMIN;
import static sh.model.Role.PROFESSOR;

public class AdminProfessorFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;

        User user = (User) httpRequest.getSession().getAttribute("user");
        if (user != null && (user.getRole() == ADMIN || user.getRole() == PROFESSOR)) {
            chain.doFilter(req, resp);
        } else {
            httpRequest.getRequestDispatcher("access-denied").forward(httpRequest, httpResponse);
        }
    }

    public void destroy() {
    }

}
