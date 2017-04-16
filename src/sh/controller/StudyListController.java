package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.StudyDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sh.dao.DaoFactory.DaoType.DB2;

public class StudyListController extends HttpServlet {

    private final StudyDao dao = DaoFactory.createStudyDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("studies", dao.getList());
            request.getRequestDispatcher("/WEB-INF/jsp/study-list.jsp").forward(request, response);
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
