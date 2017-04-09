package sh.service;

import sh.model.Study;

import java.util.ArrayList;
import java.util.List;


public class StudyService {

    private final List<Study> studies;

    public StudyService() {
//        List<Study> studies = asList(new Study(1, "Dima", "Brutski", 9.1, 421702),
//                new Study(2, "Nastya", "Bakanoskaya", 9.1, 421702));
        this.studies = new ArrayList<>();
    }

    public List<Study> getStudies() {
        return studies;
    }

    public Study getStudyById(long id) {
        Study Study = null;
        for (Study st : studies) {
            if (st.getId() == id) {
                Study = st;
                break;
            }
        }
        return Study;
    }

    public Study save(Study Study) {
        studies.add(Study);
        return Study;
    }

    static StudyService getInstance() {
        return Singleton.instance.service;
    }


    enum  Singleton {
        instance;

        StudyService service = new StudyService();
    }
}
