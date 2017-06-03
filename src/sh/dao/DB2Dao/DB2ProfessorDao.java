package sh.dao.DB2Dao;

import sh.dao.Collector;
import sh.dao.Exception.DAOException;
import sh.dao.ProfessorDao;
import sh.model.Professor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;

public class DB2ProfessorDao implements ProfessorDao {

    public static final String SELECT_ALL = "SELECT id, first_name, second_name, father_name, birth_date, avg_mark FROM professors;";
    public static final String SELECT_BY_ID = "SELECT id, first_name, second_name, father_name, birth_date, avg_mark FROM professors WHERE id=?;";
    public static final String DELETE_BY_ID = "DELETE FROM professors WHERE id=?;";
    public static final String INSERT = "INSERT INTO professors(first_name, second_name, father_name, birth_date, avg_mark) VALUES (?,?,?,?,?);";
    public static final String UPDATE = "UPDATE professors SET first_name=?, second_name=?, father_name=?, birth_date=?, avg_mark=? WHERE id=?;";
    private final DB2JDBCTemplate<Professor> template;

    private final Collector<Professor> collector = new Collector<Professor>() {
        @Override
        public Professor collect(ResultSet rs) throws SQLException {
            Professor professor = new Professor();
            professor.setId(rs.getLong("id"));
            professor.setFirstName(rs.getString("first_name"));
            professor.setSecondName(rs.getString("second_name"));
            professor.setFatherName(rs.getString("father_name"));
            professor.setBirthDate(rs.getDate("birth_date"));
            professor.setAvgMark(rs.getDouble("avg_mark"));
            return professor;
        }
    };

    public DB2ProfessorDao(DB2JDBCTemplate<Professor> template) {
        this.template = template;
    }

    @Override
    public List<Professor> getList() throws DAOException {
        return template.executeSelect(SELECT_ALL, new Object[]{}, collector);
    }

    @Override
    public Professor findOne(Long id) throws DAOException {
        List<Professor> result = template.executeSelect(SELECT_BY_ID, new Object[]{id}, collector);
        if (result.size() == 1) return result.get(0);
        else throw new DAOException();
    }

    @Override
    public int remove(Long id) throws DAOException {
        if (template.executeUpdate(DELETE_BY_ID, new Object[]{id}) == 1) return 1;
        else throw new DAOException(format("incorrect remove professor %s", id));
    }

    @Override
    public Professor saveOrUpdate(Long id, Professor entity) throws DAOException {
        if (id == 0) {
            Object[] params = {entity.getFirstName(), entity.getSecondName(),
                    entity.getFatherName(), entity.getBirthDate(),
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
                    entity.getFatherName(), entity.getBirthDate(),
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
