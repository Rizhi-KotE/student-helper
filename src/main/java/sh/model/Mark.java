package sh.model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Mark {
    private long id;
    private long studyId;
    private long studentId;
    private Date date;
    private long professorId;
    private int mark;
    private String comments;

    public Mark() {
        date = new Date(0);
    }

    public static Mark parseRequest(HttpServletRequest request) throws ServletException {
        Mark mark = new Mark();
        mark.setId(parseLong(request.getParameter("id")));
        mark.setStudyId(parseLong(request.getParameter("studyId")));
        mark.setStudentId(parseLong(request.getParameter("studentId")));
        Date date;
        try {
            String birth = request.getParameter("date");
            date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(birth).getTime());
        } catch (ParseException e) {
            throw new ServletException(e);
        }
        mark.setDate(date);
        mark.setProfessorId(parseLong(request.getParameter("professorId")));
        mark.setMark(parseInt(request.getParameter("mark")));
        mark.setComments(request.getParameter("comments"));
        return mark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudyId() {
        return studyId;
    }

    public void setStudyId(long studyId) {
        this.studyId = studyId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
