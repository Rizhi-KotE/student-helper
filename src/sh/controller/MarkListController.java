package sh.controller;

import sh.model.Group;
import sh.model.Mark;
import sh.service.MarkService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MarkListController extends HttpServlet {

    MarkService service = MarkService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Mark> marks = service.getMarks();
        request.setAttribute("marks", marks);
        request.getRequestDispatcher("WEB-INF/jsp/marks.jsp").forward(request, response);
    }
}
