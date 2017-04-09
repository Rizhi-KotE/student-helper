package sh.service;

import sh.model.Professor;

import java.util.ArrayList;
import java.util.List;


public class ProfessorService {

    private final List<Professor> professors;

    public ProfessorService() {
//        List<Professor> professors = asList(new Professor(1, "Dima", "Brutski", 9.1, 421702),
//                new Professor(2, "Nastya", "Bakanoskaya", 9.1, 421702));
        this.professors = new ArrayList<>();
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public Professor getProfessorById(long id) {
        Professor Professor = null;
        for (Professor st : professors) {
            if (st.getId() == id) {
                Professor = st;
                break;
            }
        }
        return Professor;
    }

    public Professor save(Professor Professor) {
        professors.add(Professor);
        return Professor;
    }

    public static ProfessorService getInstance() {
        return Singleton.instance.service;
    }


    enum  Singleton {
        instance;

        ProfessorService service = new ProfessorService();
    }
}
