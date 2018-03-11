package sh.controller;

import sh.dao.*;
import sh.dao.Exception.DAOException;
import sh.model.Mark;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;
import static sh.dao.DaoFactory.DaoType.DB2;

public class MarkReadController extends HttpServlet {

    final MarksDao dao = DaoFactory.createMarksDao(DB2);
    final ProfessorDao professorDao = DaoFactory.createProfessorDao(DB2);
    private StudentDao studentDao = DaoFactory.createStudentDao(DB2);
    private StudyDao studyDao = DaoFactory.createStudyDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            if (id != null) {
                Mark mark = dao.findOne(parseLong(id));
                if (mark == null) {
                    request.setAttribute("mark", new Mark());
                    request.getRequestDispatcher("resource-not-found.html").forward(request, response);
                } else {
                    request.setAttribute("mark", mark);
                    request.setAttribute("professors", professorDao.getList());
                    request.setAttribute("studies", studyDao.getList());
                    request.setAttribute("students", studentDao.getList());
                    request.getRequestDispatcher("/WEB-INF/jsp/mark-form.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("mark", new Mark());
                request.setAttribute("professors", professorDao.getList());
                request.setAttribute("studies", studyDao.getList());
                request.setAttribute("students", studentDao.getList());
                request.getRequestDispatcher("/WEB-INF/jsp/mark-form.jsp").forward(request, response);
            }
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
