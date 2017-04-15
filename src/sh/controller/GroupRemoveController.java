package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.GroupDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sh.dao.DaoFactory.DaoType.DB2;

public class GroupRemoveController extends HttpServlet {

    GroupDao dao = DaoFactory.createGroupDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groupNumber = request.getParameter("groupNumber");
        try {
            dao.remove(groupNumber);
            request.setAttribute("message", "success");
            response.sendRedirect("/group/list");
        } catch (DAOException e) {
            request.setAttribute("message", "fail");
            request.getRequestDispatcher("/WEB-INF/jsp/group-form.jsp").forward(request, response);
        }
    }
}
