package sh.dao.DB2Dao;

import sh.dao.Collector;
import sh.dao.Exception.DAOException;
import sh.dao.GroupDao;
import sh.model.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;

public class DB2GroupDao implements GroupDao {

    public static final String DELETE_BY_ID = "DELETE FROM groups WHERE group_number=?";
    public static final String INSERT = "INSERT INTO groups(group_number, avg_mark) VALUES(?, ?)";
    public static final String UPDATE = "UPDATE groups SET group_number = ?, avg_mark = ? WHERE group_number = ?";
    private static final String SELECT_ALL = "SELECT * FROM groups";
    private static final String SELECT_BY_ID = "SELECT * FROM groups WHERE group_number=?";
    private final Collector<Group> collector = new Collector<Group>() {
        @Override
        public Group collect(ResultSet rs) throws SQLException {
            Group group = new Group();
            group.setGroupNumber(rs.getString("group_number"));
            group.setAvgMark(rs.getDouble("avg_mark"));
            return group;
        }
    };
    private final DB2JDBCTemplate<Group> template;

    public DB2GroupDao(DB2JDBCTemplate<Group> template) {
        this.template = template;
    }

    @Override
    public List<Group> getList() throws DAOException {
        return template.executeSelect(SELECT_ALL, new Object[]{}, collector);
    }

    @Override
    public Group findOne(String id) throws DAOException {
        List<Group> result = template.executeSelect(SELECT_BY_ID, new Object[]{id}, collector);
        if (result.size() == 1) return result.get(0);
        else throw new DAOException();
    }

    @Override
    public int remove(String number) throws DAOException {
        if (template.executeUpdate(DELETE_BY_ID, new Object[]{number}) == 1) return 1;
        else throw new DAOException(format("incorrect remove group %s", number));
    }

    @Override
    public Group saveOrUpdate(String number, Group entity) throws DAOException {
        if ("".equals(number)) {
            Object[] params = {entity.getGroupNumber(), entity.getAvgMark()};
            if (template.executeUpdate(INSERT, params) == 1) {
                return entity;
            } else {
                throw new DAOException(format("incorrect save %s", entity));
            }
        } else {
            if (template.executeUpdate(UPDATE,
                    new Object[]{entity.getGroupNumber(), entity.getAvgMark(), number}) == 1) {
                return entity;
            } else throw new DAOException(format("no entity for update %s", entity));
        }
    }
}
