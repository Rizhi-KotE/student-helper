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

import static sh.dao.DaoFactory.DaoType.DB2;


public class StudentsController extends HttpServlet {

    private final StudentDao dao = DaoFactory.createStudentDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setAttribute("students", dao.getList());
            request.getRequestDispatcher("WEB-INF/jsp/students.jsp").forward(request, response);
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String firstName = request.getParameter("firstName");
//        String secondName = request.getParameter("secondName");
//        double avgMark = Double.valueOf(request.getParameter("avgMark"));
//        int groupNumber = Integer.valueOf(request.getParameter("groupMark"));
//        Student newStudent = new Student(0, firstName, secondName, avgMark, groupNumber);
    }
}
