package sh.dao.DB2Dao;

import sh.dao.Collector;
import sh.dao.Exception.DAOException;
import sh.dao.StudyDao;
import sh.model.Study;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;

public class DB2StudyDao implements StudyDao {

    private static final String SELECT_BY_ID = "SELECT * FROM studies WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM studies";
    private static final String INSERT = "INSERT INTO studies(name, hours, professor_id, avg_mark) VALUES (?,?,?,?);";
    private static final String UPDATE = "UPDATE studies SET name=?,hours=?, professor_id=?, avg_mark=? WHERE id=?;";
    private static final String DELETE_BY_ID = "DELETE FROM studies WHERE id=?";
    private final DB2JDBCTemplate<Study> template;
    private final Collector<Study> collector = new Collector<Study>() {
        @Override
        public Study collect(ResultSet rs) throws SQLException {
            Study study = new Study();
            study.setId(rs.getLong("id"));
            study.setName(rs.getString("name"));
            study.setHours(rs.getDouble("hours"));
            study.setProfessorId(rs.getLong("professor_id"));
            study.setAvgMark(rs.getDouble("avg_mark"));
            return study;
        }
    };

    public DB2StudyDao(DB2JDBCTemplate<Study> template) {
        this.template = template;
    }

    @Override
    public List<Study> getList() throws DAOException {
        return template.executeSelect(SELECT_ALL, new Object[]{}, collector);
    }

    @Override
    public Study findOne(Long id) throws DAOException {
        List<Study> result = template.executeSelect(SELECT_BY_ID, new Object[]{id}, collector);
        if (result.size() == 1) return result.get(0);
        else throw new DAOException();
    }

    @Override
    public int remove(Long id) throws DAOException {
        if (template.executeUpdate(DELETE_BY_ID, new Object[]{id}) == 1) return 1;
        else throw new DAOException(format("incorrect remove study %s", id));
    }

    @Override
    public Study saveOrUpdate(Long id, Study entity) throws DAOException {
        if (id == 0) {
            List<Object[]> objects = template.executeAndReturnKey(INSERT,
                    new Object[]{entity.getName(), entity.getHours(), entity.getProfessorId(), entity.getAvgMark()},
                    new String[]{"id"});
            if (objects.size() == 1) {
                entity.setId((Long) objects.get(0)[0]);
                return entity;
            } else {
                throw new DAOException(format("incorrect save %s", entity));
            }
        } else {
            if (template.executeUpdate(UPDATE,
                    new Object[]{entity.getName(), entity.getHours(), entity.getProfessorId(), entity.getAvgMark(), entity.getId()}) == 1) {
                return entity;
            }else {
                throw new DAOException(format("incorrect update %s", entity));
            }
        }
    }
}
