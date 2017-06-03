package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.StudyDao;
import sh.dao.UserDao;
import sh.model.Study;
import sh.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;
import static sh.dao.DaoFactory.DaoType.DB2;

public class UserReadController extends HttpServlet {

    private final UserDao dao = DaoFactory.createUserDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String message = (String) request.getSession().getAttribute("message");
            if(message!=null) {
                request.getSession().setAttribute("message", null);
                request.setAttribute("message", message);
            }
            String username = request.getParameter("user");
            if (username != null) {
                User userDto = dao.findOne(username);
                if (userDto == null) {
                    request.setAttribute("userDto", new User());
                    request.getRequestDispatcher("resource-not-found.html").forward(request, response);
                } else {
                    request.setAttribute("userDto", userDto);
                    request.getRequestDispatcher("/WEB-INF/jsp/user-form.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("study", new User());
                request.getRequestDispatcher("/WEB-INF/jsp/user-form.jsp").forward(request, response);
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
