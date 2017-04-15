package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.ProfessorDao;
import sh.model.Group;
import sh.model.Professor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;
import static sh.dao.DaoFactory.DaoType.DB2;

public class ProfessorCreateController extends HttpServlet {

    ProfessorDao dao = DaoFactory.createProfessorDao(DB2);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long oldId = parseLong(request.getParameter("oldId"));
        Professor professor = Professor.parseRequest(request);
        try {

            professor = dao.saveOrUpdate(oldId, professor);
            request.setAttribute("message", "success");
        } catch (DAOException e) {
            request.setAttribute("message", "fail");
        }
        request.setAttribute("message", "All right");
        request.setAttribute("professor", professor);
        request.setAttribute("action", "edit");
        request.getRequestDispatcher("WEB-INF/jsp/professor-form.jsp").forward(request, response);

    }
}
