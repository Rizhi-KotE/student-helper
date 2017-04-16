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
import static java.lang.String.format;
import static sh.dao.DaoFactory.DaoType.DB2;

public class ProfessorCreateController extends HttpServlet {


    final ProfessorDao dao = DaoFactory.createProfessorDao(DB2);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Professor professor = Professor.parseRequest(request);
        long oldId = parseLong(request.getParameter("oldId"));
        String message;
        try {
            professor = dao.saveOrUpdate(oldId, professor);
            message = "success";
        } catch (DAOException e) {
            e.printStackTrace();
            message = "fail";
        }
        request.getSession().setAttribute("message", message);
        response.sendRedirect(format("%s/professor/read?id=%d", request.getContextPath(), professor.getId()));

    }
}
