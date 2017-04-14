package sh.model;

import javax.servlet.http.HttpServletRequest;

import static java.lang.Double.parseDouble;

public class Group {
    private String groupNumber;
    private double avgMark;

    public static Group parseRequest(String groupNumber, double avgMark) {
        return null;
    }

    public static Group parseRequest(HttpServletRequest request) {
        Group group = new Group();
        group.setGroupNumber(request.getParameter("groupNumber"));
        group.setAvgMark(parseDouble(request.getParameter("avgMark")));
        return group;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }
}
