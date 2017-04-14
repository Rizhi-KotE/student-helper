package sh.dao.DB2Dao;

import sh.dao.Collector;
import sh.dao.Exception.DAOException;
import sh.dao.StudyDao;
import sh.model.Student;
import sh.model.Study;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DB2StudyDao implements StudyDao {

    public static final String SELECT_BY_ID = "SELECT * FROM studies WHERE id=?";
    public static final String SELECT_ALL = "SELECT * FORM studies";
    private final DB2JDBCTemplate<Study> template;
    private final Collector<Study> collector = new Collector<Study>() {
        @Override
        public Study collect(ResultSet rs) throws SQLException {
            Study study = new Study();
            private long id;
            private String name;
            private double hours;
            private int professorId;
            private double avgMark;
            study.setId(rs.getLong("id"));

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
        if(result.size()==1)return result.get(0);
        else throw new DAOException();
    }

    @Override
    public int remove(Long id) throws DAOException {
        throw new RuntimeException();
    }

    @Override
    public int save(Long id, Study entity) throws DAOException {
        throw new RuntimeException();
    }
}
