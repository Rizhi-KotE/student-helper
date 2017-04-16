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

import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static sh.dao.DaoFactory.DaoType.DB2;

public class UserRemoveController extends HttpServlet {

    final UserDao dao = DaoFactory.createUserDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        try {
            dao.remove(user);
//            request.getSession().setAttribute("message", "success");
            response.sendRedirect(format("%s/user/list", request.getContextPath()));
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("message", "fail");
            request.getRequestDispatcher(format("/user/read?user=%s", user)).forward(request, response);
        }
    }
}
