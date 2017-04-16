package sh.dao;

import sh.dao.DB2Dao.*;
import sh.model.*;

public class DaoFactory {
    public static GroupDao createGroupDao(DaoType type) {
        return new DB2GroupDao(new DB2JDBCTemplate<Group>());
    }

    public static MarksDao createMarksDao(DaoType type) {
        return new DB2MarkDao(new DB2JDBCTemplate<Mark>());
    }

    public static ProfessorDao createProfessorDao(DaoType type) {
        return new DB2ProfessorDao(new DB2JDBCTemplate<Professor>());
    }

    public static StudentDao createStudentDao(DaoType type) {
        return new DB2StudentDao(new DB2JDBCTemplate<Student>());
    }

    public static StudyDao createStudyDao(DaoType type) {
        return new DB2StudyDao(new DB2JDBCTemplate<Study>());
    }

    public static UserDao createUserDao(DaoType type) {
        return new DB2UserDao(new DB2JDBCTemplate<User>());
    }

    public enum DaoType {
        DB2
    }
}
