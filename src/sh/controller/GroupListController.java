package sh.controller;

import sh.model.Group;
import sh.service.GroupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GroupListController extends HttpServlet {

    GroupService service = GroupService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Group> groups = service.getGroups();
        request.setAttribute("groups", groups);
        request.getRequestDispatcher("WEB-INF/jsp/groups.jsp").forward(request, response);
    }
}
