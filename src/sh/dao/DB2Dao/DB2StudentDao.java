package sh.dao.DB2Dao;

import sh.dao.Collector;
import sh.dao.Exception.DAOException;
import sh.dao.StudentDao;
import sh.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DB2StudentDao implements StudentDao {

    public static final String SELECT_BY_ID = "SELECT * FROM students WHERE id=?";
    public static final String SELECT_ALL = "SELECT * FROM students";
    private final DB2JDBCTemplate<Student> template;
    private final Collector<Student> collector = new Collector<Student>() {
        @Override
        public Student collect(ResultSet rs) throws SQLException {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setFirstName(rs.getString("first_name"));
            student.setSecondName(rs.getString("second_name"));
            student.setAvgMark(rs.getDouble("avg_mark"));
            student.setGroupNumber(rs.getString("group_number"));
            return student;
        }
    };

    public DB2StudentDao(DB2JDBCTemplate<Student> template) {
        this.template = template;
    }

    @Override
    public List<Student> getList() throws DAOException {
        return template.executeSelect(SELECT_ALL, new Object[]{}, collector);
    }

    @Override
    public Student findOne(Long id) throws DAOException {
        List<Student> result = template.executeSelect(SELECT_BY_ID, new Object[]{id}, collector);
        if (result.size() == 1) return result.get(0);
        else throw new DAOException();
    }

    @Override
    public int remove(Long id) throws DAOException {
        throw new RuntimeException();
    }

    @Override
    public int save(Long id, Student entity) throws DAOException {
        throw new RuntimeException();
    }
}
