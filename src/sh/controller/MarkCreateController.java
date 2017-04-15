package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.MarksDao;
import sh.model.Mark;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;
import static sh.dao.DaoFactory.DaoType.DB2;

public class MarkCreateController extends HttpServlet {

    MarksDao dao = DaoFactory.createMarksDao(DB2);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mark mark = Mark.parseRequest(request);
        Long oldId = parseLong(request.getParameter("oldId"));
        try {
            mark = dao.saveOrUpdate(oldId, mark);
            request.setAttribute("message", "success");
        } catch (DAOException e) {
            request.setAttribute("message", "fail");
        }
        request.setAttribute("message", "All right");
        request.setAttribute("mark", mark);
        request.setAttribute("action", "edit");
        request.getRequestDispatcher("WEB-INF/jsp/mark-form.jsp").forward(request, response);
    }
}
