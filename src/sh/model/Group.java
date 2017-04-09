package sh.model;

public class Group {
    private long id;
    private int groupNumber;
    private double avgMark;

    public Group(long id, int groupNumber, double avgMark) {
        this.id = id;
        this.groupNumber = groupNumber;
        this.avgMark = avgMark;
    }

    public Group() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }
}
