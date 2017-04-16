package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.StudentDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sh.dao.DaoFactory.DaoType.DB2;

public class StudyRemoveController extends HttpServlet {

    final StudentDao dao = DaoFactory.createStudentDao(DB2);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        try {
            dao.remove(id);
            request.setAttribute("message", "success");
            request.getRequestDispatcher("/study/list").forward(request, response);
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("message", "fail");
            request.getRequestDispatcher("/WEB-INF/jsp/study-form.jsp").forward(request, response);
        }
    }
}
