package sh.controller;

import sh.dao.DaoFactory;
import sh.dao.Exception.DAOException;
import sh.dao.MarksDao;
import sh.model.Group;
import sh.model.Mark;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;
import static sh.dao.DaoFactory.DaoType.DB2;

public class MarkController extends HttpServlet {

    MarksDao dao = DaoFactory.createMarksDao(DB2);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            if (id != null) {
                Mark mark = dao.findOne(parseLong(id));
                if (mark == null) {
                    request.setAttribute("mark", new Group());
                    request.getRequestDispatcher("resource-not-found.html").forward(request, response);
                } else {
                    request.setAttribute("mark", mark);
                    request.setAttribute("action", "edit");
                    request.getRequestDispatcher("WEB-INF/jsp/mark-form.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("mark", new Mark());
                request.setAttribute("action", "saveOrUpdate");
                request.getRequestDispatcher("WEB-INF/jsp/mark-form.jsp").forward(request, response);
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        double avgMark = parseDouble(request.getParameter("avgMark"));
//        String firstName = request.getParameter("firstName");
//        String secondName = request.getParameter("secondName");
//        String fatherName = request.getParameter("fatherName");
//        Date birthDate = null;
//        try {
//            String birth = request.getParameter("birthDate");
//            java.util.Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
//            birthDate = new Date(parse.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        int id = parseInt(request.getParameter("id"));
//
//        Professor professor = new Professor(id, firstName, secondName, fatherName, birthDate, avgMark);
//        Professor saveOrUpdate = service.saveOrUpdate(professor);
//
//        request.setAttribute("message", "All right");
//        request.setAttribute("professor", saveOrUpdate);
//        request.setAttribute("action", "edit");
//        request.getRequestDispatcher("WEB-INF/jsp/professor-form.jsp").forward(request, response);
    }
}
