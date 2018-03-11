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

import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static sh.dao.DaoFactory.DaoType.DB2;

public class UserCreateController extends HttpServlet {

    private final UserDao dao = DaoFactory.createUserDao(DB2);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = User.parseRequest(request);
        String oldUser = request.getParameter("oldUser");
        String message;
        try {
            user = dao.saveOrUpdate(oldUser, user);
            message = "success";
        } catch (DAOException e) {
            e.printStackTrace();
            message = "fail";
        }
        request.getSession().setAttribute("message", message);
        response.sendRedirect(format("%s/user/read?user=%s", request.getContextPath(), user.getUser()));
    }
}
