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

public class GroupCreateController extends HttpServlet {
    private final GroupDao dao = DaoFactory.createGroupDao(DB2);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Group group = Group.parseRequest(request);
        String oldNumber = request.getParameter("oldNumber");
        try {

            group = dao.saveOrUpdate(oldNumber, group);
            request.setAttribute("message", "success");
        } catch (DAOException e) {
            request.setAttribute("message", "fail");
        }
        request.setAttribute("message", "All right");
        request.setAttribute("group", group);
        request.setAttribute("action", "edit");
        request.getRequestDispatcher("WEB-INF/jsp/group-form.jsp").forward(request, response);
    }
}
