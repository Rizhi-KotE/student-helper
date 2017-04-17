package sh.controller;

import sh.dao.DB2Dao.DB2JDBCTemplate;
import sh.dao.Exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class RecalculateServlet extends HttpServlet {

    private final DB2JDBCTemplate template = new DB2JDBCTemplate();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int target = parseInt(request.getParameter("target"));
        try {
            template.callProcedure("CALL update_avg_marks(?);", new Object[]{target});
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
