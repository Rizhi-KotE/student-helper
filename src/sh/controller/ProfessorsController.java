package sh.controller;

import sh.model.Professor;
import sh.service.ProfessorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProfessorsController extends HttpServlet {
    ProfessorService service = ProfessorService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Professor> professors = service.getProfessors();
        request.setAttribute("professors", professors);
        request.getRequestDispatcher("WEB-INF/jsp/professors.jsp").forward(request, response);
    }
}
