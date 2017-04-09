package sh.model;

import java.sql.Date;

public class Professor {
    private long id;
    private String firstName;
    private String secondName;
    private String fatherName;
    private Date birthDate;
    private double avgMark;

    public Professor() {
        birthDate = new Date(0);
    }

    public Professor(long id, String firstName, String secondName, String fatherName, Date birthDate, double avgMark) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.avgMark = avgMark;
    }

    public long getId() {

        return id;
    }

    public Professor(long id) {
        this.id = id;
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
}
