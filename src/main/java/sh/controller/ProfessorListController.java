package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.ProfessorDao;
import sh.model.Professor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static sh.dao.DaoFactory.DaoType.DB2;

public class ProfessorListController extends HttpServlet {
    final ProfessorDao dao = DaoFactory.createProfessorDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Professor>    professors = dao.getList();
            request.setAttribute("professors", professors);
            request.getRequestDispatcher("/WEB-INF/jsp/professor-list.jsp").forward(request, response);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
