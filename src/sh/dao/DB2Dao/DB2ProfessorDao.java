package sh.dao.DB2Dao;

import sh.dao.Collector;
import sh.dao.Exception.DAOException;
import sh.dao.ProfessorDao;
import sh.model.Professor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DB2ProfessorDao implements ProfessorDao {

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
        return null;
    }

    @Override
    public Professor findOne(Long id) throws DAOException {
        return null;
    }

    @Override
    public int remove(Long id) throws DAOException {
        throw new RuntimeException();
    }

    @Override
    public int save(Long id, Professor entity) throws DAOException {
        throw new RuntimeException();
    }
}
