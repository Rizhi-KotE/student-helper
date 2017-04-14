package sh.model;

import javax.servlet.http.HttpServletRequest;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

public class Student {
    private String firstName;
    private String secondName;
    private double avgMark;
    private String groupNumber;
    private long id;

    public static Student parseRequest(HttpServletRequest request) {
        Student student = new Student();
        student.setFirstName(request.getParameter("firstName"));
        student.setSecondName(request.getParameter("secondName"));
        student.setAvgMark(parseDouble(request.getParameter("avgMark")));
        student.setGroupNumber(request.getParameter("groupNumber"));
        student.setId(parseLong(request.getParameter("id")));
        return student;
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

    public double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
