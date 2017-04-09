package sh.service;

import sh.model.Student;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;


public class StudentService {

    private final List<Student> students;

    public StudentService() {
        List<Student> students = asList(new Student(1, "Dima", "Brutski", 9.1, 421702),
                new Student(2, "Nastya", "Bakanoskaya", 9.1, 421702));
        this.students = new ArrayList<>(students);
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudentById(long id) {
        Student student = null;
        for (Student st : students) {
            if (st.getId() == id) {
                student = st;
                break;
            }
        }
        return student;
    }

    public Student save(Student student) {
        students.add(student);
        return student;
    }

    public static StudentService getInstance() {
        return Singleton.instance.service;
    }


    enum  Singleton {
        instance;

        StudentService service = new StudentService();
    }
}
