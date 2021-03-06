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

import static sh.dao.DaoFactory.DaoType.DB2;

public class GroupReadController extends HttpServlet {
    private final GroupDao dao = DaoFactory.createGroupDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String message = (String) request.getSession().getAttribute("message");
            if(message!=null) {
                request.getSession().setAttribute("message", null);
                request.setAttribute("message", message);
            }
            String id = request.getParameter("groupNumber");
            if (id != null) {
                Group group = dao.findOne(id);
                if (group == null) {
                    request.setAttribute("group", new Group());
                    request.getRequestDispatcher("resource-not-found.html").forward(request, response);
                } else {
                    request.setAttribute("group", group);
                    request.getRequestDispatcher("/WEB-INF/jsp/group-form.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("group", new Group());
                request.getRequestDispatcher("/WEB-INF/jsp/group-form.jsp").forward(request, response);
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
