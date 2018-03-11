package sh.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rizhi-kote on 11.03.18.
 */
@WebServlet(name = "UnloginServlet", urlPatterns = "/unlogin")
public class HttpServlet extends javax.servlet.http.HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.removeAttribute("USER");
        resp.sendRedirect(req.getContextPath());
    }
}
