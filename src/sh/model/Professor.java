package sh.model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

public class Professor {
    private long id;
    private String firstName;
    private String secondName;
    private String fatherName;
    private Date birthDate;
    private double avgMark;

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }

    public static Professor parseRequest(HttpServletRequest request) throws ServletException {
        Professor professor = new Professor();
        Date birthDate;
        try {
            String birth = request.getParameter("birthDate");
            java.util.Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
            birthDate = new Date(parse.getTime());
        } catch (ParseException e) {
            throw new ServletException(e);
        }
        professor.id = parseLong(request.getParameter("id"));
        professor.avgMark= parseDouble(request.getParameter("avgMark"));
        professor.firstName = request.getParameter("firstName");
        professor.secondName = request.getParameter("secondName");
        professor.fatherName = request.getParameter("fatherName");
        professor.birthDate = birthDate;
        return professor;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", birthDate=" + birthDate +
                ", avgMark=" + avgMark +
                '}';
    }
}
