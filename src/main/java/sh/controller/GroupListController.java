package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.GroupDao;
import sh.model.Group;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static sh.dao.DaoFactory.DaoType.DB2;

public class GroupListController extends HttpServlet {

    private final GroupDao dao = DaoFactory.createGroupDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Group> groups = dao.getList();
            request.setAttribute("groups", groups);
            request.getRequestDispatcher("/WEB-INF/jsp/group-list.jsp").forward(request, response);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
