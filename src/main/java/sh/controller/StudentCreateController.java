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
import static java.lang.String.format;
import static sh.dao.DaoFactory.DaoType.DB2;

public class StudentCreateController extends HttpServlet {

    private final StudentDao dao = DaoFactory.createStudentDao(DB2);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = Student.parseRequest(request);
        long oldId = parseLong(request.getParameter("oldId"));
        String message;
        try {
            student = dao.saveOrUpdate(oldId, student);
            message = "success";
        } catch (DAOException e) {
            e.printStackTrace();
            message = "fail";
        }
        request.getSession().setAttribute("message", message);
        response.sendRedirect(format("%s/student/read?id=%d", request.getContextPath(), student.getId()));

    }
}
