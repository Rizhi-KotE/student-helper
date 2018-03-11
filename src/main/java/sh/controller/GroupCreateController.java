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

import static java.lang.String.format;
import static sh.dao.DaoFactory.DaoType.DB2;

public class GroupCreateController extends HttpServlet {
    private final GroupDao dao = DaoFactory.createGroupDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Group group = Group.parseRequest(request);
        String oldNumber = request.getParameter("oldNumber");
        String message;
        try {

            group = dao.saveOrUpdate(oldNumber, group);
            message = "success";
        } catch (DAOException e) {
            e.printStackTrace();
            message = "fail";
        }
        request.getSession().setAttribute("message", message);
        response.sendRedirect(format("%s/group/read?groupNumber=%s", request.getContextPath(), group.getGroupNumber()));
    }
}
