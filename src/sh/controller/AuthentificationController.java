package sh.controller;


import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.UserDao;
import sh.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sh.dao.DaoFactory.DaoType.DB2;


public class AuthentificationController extends HttpServlet {

    private final UserDao dao = DaoFactory.createUserDao(DB2);

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
        try {
            return dao.getByUsernameAndPassword(username, password);
        } catch (DAOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
