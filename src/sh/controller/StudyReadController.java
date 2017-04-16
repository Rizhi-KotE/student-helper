package sh.controller;

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

public class StudyReadController extends HttpServlet {

    private final StudyDao dao = DaoFactory.createStudyDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            if (id != null) {
                Study study = dao.findOne(parseLong(id));
                if (study == null) {
                    request.setAttribute("study", new Study());
                    request.getRequestDispatcher("resource-not-found.html").forward(request, response);
                } else {
                    request.setAttribute("study", study);
                    request.getRequestDispatcher("/WEB-INF/jsp/study-form.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("study", new Study());
                request.getRequestDispatcher("/WEB-INF/jsp/study-form.jsp").forward(request, response);
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
