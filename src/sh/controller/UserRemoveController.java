package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.StudentDao;
import sh.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sh.dao.DaoFactory.DaoType.DB2;

public class UserRemoveController extends HttpServlet {

    final UserDao dao = DaoFactory.createUserDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        try {
            dao.remove(user);
            request.setAttribute("message", "success");
            response.sendRedirect("/user/list");
        } catch (DAOException e) {
            request.setAttribute("message", "fail");
            request.getRequestDispatcher("/WEB-INF/jsp/user-form.jsp").forward(request, response);
        }
    }
}
