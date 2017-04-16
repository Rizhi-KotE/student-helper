package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.ProfessorDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static sh.dao.DaoFactory.DaoType.DB2;

public class ProfessorRemoveController extends HttpServlet {

    final ProfessorDao dao = DaoFactory.createProfessorDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = parseLong(request.getParameter("id"));
        try {
            dao.remove(id);
//            request.getSession().setAttribute("message", "success");
            response.sendRedirect(format("%s/professor/list", request.getContextPath()));
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("message", "fail");
            request.getRequestDispatcher(format("/professor/read?id=%d", id)).forward(request, response);
        }
    }
}
