package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.StudentDao;
import sh.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static sh.dao.DaoFactory.DaoType.DB2;

public class StudentFormController extends HttpServlet {

    private final StudentDao dao = DaoFactory.createStudentDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                String id = request.getParameter("id");
                if (id != null) {
                    Student student = null;
                student = dao.findOne(parseLong(id));
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
                    request.setAttribute("action", "save");
                    request.getRequestDispatcher("WEB-INF/jsp/student-form.jsp").forward(request, response);
                }
            } catch (DAOException e) {
                throw new ServletException(e);
            }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Student student = Student.parseRequest(request);
            long id = parseLong(request.getParameter("oldId"));
            if (dao.save(id, student) == 1) {
                request.setAttribute("message", "success");
            }else {
                request.setAttribute("message", "fail");
            }
            request.setAttribute("message", "All right");
            request.setAttribute("student", student);
            request.setAttribute("action", "edit");
            request.getRequestDispatcher("WEB-INF/jsp/student-form.jsp").forward(request, response);
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
