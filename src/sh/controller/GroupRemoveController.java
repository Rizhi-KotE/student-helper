package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.GroupDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;
import static sh.dao.DaoFactory.DaoType.DB2;

public class GroupRemoveController extends HttpServlet {

    GroupDao dao = DaoFactory.createGroupDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String groupNumber = request.getParameter("groupNumber");
            if (dao.remove(groupNumber) == 1) {
                request.setAttribute("message", "success");
                response.sendRedirect("/group/list");
            } else {
                request.setAttribute("message", "fail");
                request.getRequestDispatcher("/WEB-INF/jsp/group-form.jsp").forward(request, response);
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
