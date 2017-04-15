package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.GroupDao;
import sh.dao.MarksDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sh.dao.DaoFactory.DaoType.DB2;

public class MarkRemoveController extends HttpServlet {

    MarksDao dao = DaoFactory.createMarksDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        try {
            dao.remove(id);
            request.setAttribute("message", "success");
            response.sendRedirect("/mark/list");
        } catch (DAOException e) {
            request.setAttribute("message", "fail");
            request.getRequestDispatcher("/WEB-INF/jsp/mark-form.jsp").forward(request, response);
        }
    }
}