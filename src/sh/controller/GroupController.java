package sh.controller;

import sh.model.Group;
import sh.model.Student;
import sh.service.GroupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class GroupController extends HttpServlet {
    GroupService service = GroupService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            Group group = service.getGroupById(parseLong(id));
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
            request.setAttribute("action", "create");
            request.getRequestDispatcher("WEB-INF/jsp/group-form.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double avgMark = parseDouble(request.getParameter("avgMark"));
        int groupNumber = parseInt(request.getParameter("groupNumber"));
        int id = parseInt(request.getParameter("id"));

        Group group = new Group(id, groupNumber, avgMark);
        Group save = service.save(group);

        request.setAttribute("message", "All right");
        request.setAttribute("group", save);
        request.setAttribute("action", "edit");
        request.getRequestDispatcher("WEB-INF/jsp/group-form.jsp").forward(request, response);
    }
}
