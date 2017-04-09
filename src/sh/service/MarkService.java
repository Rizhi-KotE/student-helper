package sh.service;

import sh.model.Mark;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;


public class MarkService {

    private final List<Mark> marks;

    public MarkService() {
        this.marks = new ArrayList<>();
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public Mark getMarkById(long id) {
        Mark Mark = null;
        for (Mark st : marks) {
            if (st.getId() == id) {
                Mark = st;
                break;
            }
        }
        return Mark;
    }

    public Mark save(Mark Mark) {
        marks.add(Mark);
        return Mark;
    }

    public static MarkService getInstance() {
        return Singleton.instance.service;
    }


    enum  Singleton {
        instance;

        MarkService service = new MarkService();
    }
}
