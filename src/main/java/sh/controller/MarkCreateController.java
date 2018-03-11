package sh.controller;

import sh.dao.*;
import sh.dao.Exception.DAOException;
import sh.model.Mark;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;
import static sh.dao.DaoFactory.DaoType.DB2;

public class MarkCreateController extends HttpServlet {

    private final MarksDao dao = DaoFactory.createMarksDao(DB2);
    private final ProfessorDao professorDao = DaoFactory.createProfessorDao(DB2);
    private final StudentDao studentDao = DaoFactory.createStudentDao(DB2);
    private final StudyDao studyDao = DaoFactory.createStudyDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mark mark = Mark.parseRequest(request);
        try {
            //marks cannot be modified
            mark = dao.saveOrUpdate(0L, mark);
            request.setAttribute("message", "success");
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("message", "fail");
        }

        request.getRequestDispatcher(format("/mark/read?id=%d", mark.getId())).forward(request, response);
    }
}
