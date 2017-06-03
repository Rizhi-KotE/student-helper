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
import static java.lang.String.format;
import static sh.dao.DaoFactory.DaoType.DB2;

public class StudyCreateController extends HttpServlet {

    private final StudyDao dao = DaoFactory.createStudyDao(DB2);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Study study = Study.parseRequest(request);
        long oldId = parseLong(request.getParameter("oldId"));
        String message;
        try {
            study = dao.saveOrUpdate(oldId, study);
            message = "success";
        } catch (DAOException e) {
            e.printStackTrace();
            message = "fail";
        }
        request.getSession().setAttribute("message", message);
        response.sendRedirect(format("%s/study/read?id=%d", request.getContextPath(), study.getId()));

    }

}
