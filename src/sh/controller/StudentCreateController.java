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

import static java.lang.Long.parseLong;
import static sh.dao.DaoFactory.DaoType.DB2;

public class StudentCreateController extends HttpServlet {

    private final StudentDao dao = DaoFactory.createStudentDao(DB2);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = Student.parseRequest(request);
        long id = parseLong(request.getParameter("oldId"));
        try {
            dao.saveOrUpdate(id, student);
            request.setAttribute("message", "success");
        } catch (DAOException e) {
            request.setAttribute("message", "fail");
        }
        request.setAttribute("student", student);
        request.setAttribute("action", "edit");
        request.getRequestDispatcher("WEB-INF/jsp/student-form.jsp").forward(request, response);
    }
}
