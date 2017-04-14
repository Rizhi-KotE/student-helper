package sh.dao.DB2Dao;

import sh.dao.Collector;
import sh.dao.Exception.DAOException;
import sh.dao.MarksDao;
import sh.model.Mark;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DB2MarkDao implements MarksDao {
    public static final String SELECT_BY_ID = "SELECT * FROM marks WHERE id=?";
    public static final String INSERT = "INSERT INTO marks VALUES (?,?,?,?,?,?,?)";
    public static final String UPDATE = "UPDATE marks SET study_id=?, student_id=?, date=?, professor_id=?, mark=?, comments=? WHERE id=?";
    public static final String DELETE_BY_ID = "DELETE FROM marks WHERE id=?";
    private Collector<Mark> collector = new Collector<Mark>() {
        @Override
        public Mark collect(ResultSet rs) throws SQLException {

            Mark mark = new Mark();
            mark.setId(rs.getLong("id"));
            mark.setStudyId(rs.getLong("study_id"));
            mark.setStudentId(rs.getLong("student_id"));
            mark.setDate(rs.getDate("date"));

            mark.setProfessorId(rs.getLong("professor_id"));
            mark.setMark(rs.getInt("mark"));
            mark.setComments(rs.getString("comments"));
            return mark;
        }
    };

    private DB2JDBCTemplate<Mark> template;

    public DB2MarkDao(DB2JDBCTemplate<Mark> template) {
        this.template = template;
    }

    @Override
    public List<Mark> getList() throws DAOException {
        return template.executeSelect("SELECT * FROM marks", new Object[]{}, collector);
    }

    @Override
    public Mark findOne(Long id) throws DAOException {
        List<Mark> result = template.executeSelect(SELECT_BY_ID, new Object[]{id}, collector);
        if (result.size() == 1) return result.get(0);
        else throw new DAOException();
    }

    @Override
    public int remove(Long number) throws DAOException {
        return template.executeUpdate(DELETE_BY_ID, new Object[]{number});
    }

    @Override
    public int save(Long number, Mark entity) throws DAOException {
        if (number == 0) {
            Object[] params = {entity.getId(), entity.getStudyId(), entity.getStudentId(),
                    entity.getDate(), entity.getProfessorId(),
                    entity.getMark(), entity.getComments()};
            return template.executeUpdate(INSERT,
                    params);
        } else {
            Object[] params = {entity.getStudyId(), entity.getStudentId(),
                    entity.getDate(), entity.getProfessorId(),
                    entity.getMark(), entity.getComments(), entity.getId()};
            return template.executeUpdate(UPDATE, params);

        }
    }
}
