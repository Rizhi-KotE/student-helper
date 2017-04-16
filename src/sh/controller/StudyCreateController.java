package sh.controller;

import sh.dao.Dao;
import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.StudyDao;
import sh.model.Student;
import sh.model.Study;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;
import static sh.dao.DaoFactory.DaoType.DB2;

public class StudyCreateController extends HttpServlet {

    private final StudyDao dao = DaoFactory.createStudyDao(DB2);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Study study = Study.parseRequest(request);
        try {
            dao.saveOrUpdate(study.getId(), study);
            request.setAttribute("message", "success");
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("message", "fail");
        }
        request.setAttribute("study", study);
        request.setAttribute("action", "edit");
        request.getRequestDispatcher("/WEB-INF/jsp/study-form.jsp").forward(request, response);
    }

}
