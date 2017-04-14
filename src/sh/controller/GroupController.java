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

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static sh.dao.DaoFactory.DaoType.DB2;

public class GroupController extends HttpServlet {
    private final GroupDao dao = DaoFactory.createGroupDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String id = request.getParameter("group_number");
            if (id != null) {
                Group group = dao.findOne(id);
                if (group == null) {
                    request.setAttribute("group", new Group());
                    request.getRequestDispatcher("resource-not-found.html").forward(request, response);
                } else {
                    request.setAttribute("group", group);
                    request.setAttribute("action", "edit");
                    request.getRequestDispatcher("WEB-INF/jsp/group-form.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("group", new Group());
                request.setAttribute("action", "save");
                request.getRequestDispatcher("WEB-INF/jsp/group-form.jsp").forward(request, response);
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {


            Group group = Group.parseRequest(request);
            String oldNumber = request.getParameter("oldNumber");
            if (dao.save(oldNumber, group) == 1) {
                request.setAttribute("message", "success");
            } else {
                request.setAttribute("message", "fail");
            }

            request.setAttribute("message", "All right");
            request.setAttribute("group", group);
            request.setAttribute("action", "edit");
            request.getRequestDispatcher("WEB-INF/jsp/group-form.jsp").forward(request, response);
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
