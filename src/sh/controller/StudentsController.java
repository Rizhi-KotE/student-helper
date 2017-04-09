package sh.controller;

import sh.model.Student;
import sh.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class StudentsController extends HttpServlet {

    private final StudentService studentService = StudentService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("students", studentService.getStudents());
        request.getRequestDispatcher("WEB-INF/jsp/students.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        double avgMark = Double.valueOf(request.getParameter("avgMark"));
        int groupNumber = Integer.valueOf(request.getParameter("groupMark"));
        Student newStudent = new Student(0, firstName, secondName, avgMark, groupNumber);
    }
}
