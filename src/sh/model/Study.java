package sh.model;

import javax.servlet.http.HttpServletRequest;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

public class Study {
    private long id;
    private String name;
    private double hours;
    private long professorId;
    private double avgMark;

    public static Study parseRequest(HttpServletRequest request) {
        Study study = new Study();
        study.setId(parseLong(request.getParameter("id")));
        study.setName(request.getParameter("name"));
        study.setHours(parseDouble(request.getParameter("hours")));
        study.setProfessorId(parseLong(request.getParameter("professorId")));
        study.setAvgMark(parseDouble(request.getParameter("avgMark")));
        return  study;
    }

    @Override
    public String toString() {
        return "Study{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", professorId=" + professorId +
                ", avgMark=" + avgMark +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }

    public double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }
}
