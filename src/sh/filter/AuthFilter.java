package sh.filter;

import sh.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;

        User user = (User) httpRequest.getSession().getAttribute("user");
        String url = httpRequest.getServletPath();

        if (user != null || url.endsWith(".js") || url.endsWith(".css") || url.endsWith(".html") ||
                url.endsWith(".jsp") || url.equals("/login") || "/".equals(url) || url.startsWith("/static")) {
            chain.doFilter(req, resp);
        } else {
            httpResponse.sendRedirect(format( "%s/login", httpRequest.getContextPath()));
        }
    }

    public void destroy() {
    }

}
