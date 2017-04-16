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

import static sh.dao.DaoFactory.DaoType.DB2;

public class ProfessorCreateController extends HttpServlet {


    final ProfessorDao dao = DaoFactory.createProfessorDao(DB2);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Professor professor = Professor.parseRequest(request);
        try {

            professor = dao.saveOrUpdate(professor.getId(), professor);
            request.setAttribute("message", "success");
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("message", "fail");
        }
        request.setAttribute("professor", professor);
        request.setAttribute("action", "edit");
        request.getRequestDispatcher("/WEB-INF/jsp/professor-form.jsp").forward(request, response);

    }
}
