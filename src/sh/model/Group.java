package sh.model;

import javax.servlet.http.HttpServletRequest;

import static java.lang.Double.parseDouble;

public class Group {
    private String groupNumber = "";
    private double avgMark;

    public static Group parseRequest(String groupNumber, double avgMark) {
        return null;
    }

    public static Group parseRequest(HttpServletRequest request) {
        Group group = new Group();
        String groupNumber = request.getParameter("groupNumber");
        if(groupNumber!=null){
            group.setGroupNumber(groupNumber);
        }
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

    @Override
    public String toString() {
        return "Group{" +
                "groupNumber='" + groupNumber + '\'' +
                ", avgMark=" + avgMark +
                '}';
    }
}
