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
import java.util.List;

import static sh.dao.DaoFactory.DaoType.DB2;

public class MarkListController extends HttpServlet {

    final MarksDao dao = DaoFactory.createMarksDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Mark> marks = dao.getList();
            request.setAttribute("marks", marks);
            request.getRequestDispatcher("/WEB-INF/jsp/mark-list.jsp").forward(request, response);
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }
}
