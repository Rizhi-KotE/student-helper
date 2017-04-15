package sh.dao.DB2Dao;

import sh.dao.Collector;
import sh.dao.Exception.DAOException;
import sh.dao.StudentDao;
import sh.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;

public class DB2StudentDao implements StudentDao {

    public static final String SELECT_BY_ID = "SELECT * FROM students WHERE id=?";
    public static final String SELECT_ALL = "SELECT * FROM students";
    public static final String DELETE_BY_ID = "DELETE FROM students WHERE id=?;";
    public static final String INSERT = "INSERT INTO students(first_name, second_name, avg_mark) VALUES (?,?,?);";
    public static final String UPDATE = "UPDATE students SET first_name=?, second_name=?, avg_mark=? WHERE id=?;";
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
        if (template.executeUpdate(DELETE_BY_ID, new Object[]{id}) == 1) return 1;
        else throw new DAOException(format("incorect remove student %s", id));
    }

    @Override
    public Student saveOrUpdate(Long id, Student entity) throws DAOException {
        if (id == 0) {
            Object[] params = {entity.getFirstName(), entity.getSecondName(),
                    entity.getAvgMark()};
            List<Object[]> objects = template.executeAndReturnKey(INSERT,
                    params, new String[]{"id"});
            if (objects.size() == 1) {
                entity.setId((Long) objects.get(0)[0]);
                return entity;
            } else {
                throw new DAOException(format("incorrect save %s", entity));
            }
        } else {
            Object[] params = {entity.getFirstName(), entity.getSecondName(),
                    entity.getAvgMark(), entity.getId()};
            if (template.executeUpdate(UPDATE,
                    params) == 1) {
                return entity;
            } else {
                throw new DAOException(format("incorrect update %s", entity));
            }
        }
    }
}
