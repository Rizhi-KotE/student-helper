package sh.controller;


import sh.model.User;
import sh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthentificationController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    UserService service = UserService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = getUser(username, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("choose-action");
        } else {
            request.setAttribute("message", "Login or password is incorrect");
            response.sendRedirect("login");
        }
    }

    private User getUser(String username, String password) {
        User user = service.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}
