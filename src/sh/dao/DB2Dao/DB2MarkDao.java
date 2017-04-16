package sh.dao.DB2Dao;

import sh.dao.Collector;
import sh.dao.Exception.DAOException;
import sh.dao.MarksDao;
import sh.model.Mark;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;

public class DB2MarkDao implements MarksDao {
    private static final String SELECT_BY_ID = "SELECT * FROM marks WHERE id=?";
    private static final String INSERT = "INSERT INTO marks(study_id, student_id, date, professor_id, mark, comments) VALUES (?,?,?,?,?,?);";
    private static final String UPDATE = "UPDATE marks SET study_id=?, student_id=?, date=?, professor_id=?, mark=?, comments=? WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM marks WHERE id=?";
    private final Collector<Mark> collector = new Collector<Mark>() {
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

    private final DB2JDBCTemplate<Mark> template;

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
    public int remove(Long id) throws DAOException {
        if (template.executeUpdate(DELETE_BY_ID, new Object[]{id}) == 1) return 1;
        else throw new DAOException(format("incorrect remove mark %s", id));
    }

    @Override
    public Mark saveOrUpdate(Long number, Mark entity) throws DAOException {
        if (number == 0) {
            Object[] params = {entity.getId(), entity.getStudyId(), entity.getStudentId(),
                    entity.getDate(), entity.getProfessorId(),
                    entity.getMark(), entity.getComments()};
            List<Object[]> objects = template.executeAndReturnKey(INSERT,
                    params, new String[]{"id"});
            if (objects.size() == 1) {
                entity.setId((Long) objects.get(0)[0]);
                return entity;
            } else {
                throw new DAOException(format("incorrect save %s", entity));
            }
        } else {
            Object[] params = {entity.getStudyId(), entity.getStudentId(),
                    entity.getDate(), entity.getProfessorId(),
                    entity.getMark(), entity.getComments(), entity.getId()};
            if (template.executeUpdate(UPDATE,
                    params) == 1) {
                return entity;
            }else {
                throw new DAOException(format("incorrect update %s", entity));
            }
        }
    }
}
