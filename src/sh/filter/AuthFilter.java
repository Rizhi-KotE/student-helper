package sh.filter;

import sh.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;

        User user = (User) httpRequest.getSession().getAttribute("user");
        String url = httpRequest.getRequestURI();

        if (user != null || url.endsWith(".js") || url.endsWith(".css") || url.endsWith(".html") ||
                url.endsWith(".jsp") || url.endsWith("login") || "/".equals(url)) {
            chain.doFilter(req, resp);
        } else {
            httpResponse.sendRedirect("login");
        }
    }

    public void destroy() {
    }

}
