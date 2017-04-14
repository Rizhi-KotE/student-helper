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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static sh.dao.DaoFactory.DaoType.DB2;

public class ProfessorFormServlet extends HttpServlet {

    ProfessorDao dao = DaoFactory.createProfessorDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            if (id != null) {
                Professor professor = dao.findOne(parseLong(id));
                if (professor == null) {
                    request.setAttribute("professor", new Group());
                    request.getRequestDispatcher("resource-not-found.html").forward(request, response);
                } else {
                    request.setAttribute("professor", professor);
                    request.setAttribute("action", "edit");
                    request.getRequestDispatcher("WEB-INF/jsp/professor-form.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("professor", new Professor());
                request.setAttribute("action", "save");
                request.getRequestDispatcher("WEB-INF/jsp/professor-form.jsp").forward(request, response);
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            long oldId = parseLong(request.getParameter("oldId"));


            Professor professor = Professor.parseRequest(request);
            if (dao.save(oldId, professor) == 1) {
                request.setAttribute("message", "success");
            }else {
                request.setAttribute("message", "fail");
            };

            request.setAttribute("message", "All right");
            request.setAttribute("professor", professor);
            request.setAttribute("action", "edit");
            request.getRequestDispatcher("WEB-INF/jsp/professor-form.jsp").forward(request, response);
        } catch (DAOException e) {
            throw new ServletException(e);
        }

    }
}
