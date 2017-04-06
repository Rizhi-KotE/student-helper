package sh.controller;

import sh.model.Student;
import sh.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class StudentFormController extends HttpServlet {

    private final StudentService service = StudentService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            Student student = service.getStudentById(parseLong(id));
            if (student == null) {
                request.setAttribute("student", new Student());
                request.getRequestDispatcher("resource-not-found.html").forward(request, response);
            } else {
                request.setAttribute("student", student);
                request.setAttribute("action", "edit");
                request.getRequestDispatcher("WEB-INF/jsp/student-form.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("student", new Student());
            request.setAttribute("action", "create");
            request.getRequestDispatcher("WEB-INF/jsp/student-form.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        double avgMark = parseDouble(request.getParameter("avgMark"));
        int groupNumber = parseInt(request.getParameter("groupNumber"));
        int id = parseInt(request.getParameter("id"));

        Student student = new Student(id, firstName, secondName, avgMark, groupNumber);
        Student save = service.save(student);

        request.setAttribute("message", "All right");
        request.setAttribute("student", save);
        request.setAttribute("action", "edit");
        request.getRequestDispatcher("WEB-INF/jsp/student-form.jsp").forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
