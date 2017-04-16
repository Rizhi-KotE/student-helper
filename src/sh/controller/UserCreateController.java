package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.StudentDao;
import sh.dao.UserDao;
import sh.model.Student;
import sh.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;
import static sh.dao.DaoFactory.DaoType.DB2;

public class UserCreateController extends HttpServlet {

    private final UserDao dao = DaoFactory.createUserDao(DB2);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = User.parseRequest(request);
        String id = request.getParameter("oldId");
        try {
            dao.saveOrUpdate(id, user);
            request.setAttribute("message", "success");
        } catch (DAOException e) {
            request.setAttribute("message", "fail");
        }
        request.setAttribute("user", user);
        request.setAttribute("action", "edit");
        request.getRequestDispatcher("WEB-INF/jsp/user-form.jsp").forward(request, response);
    }
}
