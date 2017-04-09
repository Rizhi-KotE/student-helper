package sh.model;

public class Student {
    private String firstName;
    private String secondName;
    private double avgMark;
    private int groupNumber;
    private long id;

    public Student() {
    }

    public Student(long id, String firstName, String secondName, double avgMark, int groupNumber) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.avgMark = avgMark;
        this.groupNumber = groupNumber;
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

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
