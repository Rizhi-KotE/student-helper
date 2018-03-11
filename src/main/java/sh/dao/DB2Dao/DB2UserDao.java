package sh.dao.DB2Dao;

import sh.dao.Collector;
import sh.dao.Exception.DAOException;
import sh.dao.UserDao;
import sh.model.Role;
import sh.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;

public class DB2UserDao implements UserDao {

    private static final String SELECT_ALL = "SELECT username, role FROM users;";
    private static final String SELECT_BY_USER = "SELECT username, role FROM users WHERE username=?;";
    private static final String DELETE_BY_USER = "DELETE FROM users WHERE user=?;";
    private static final String INSERT = "INSERT INTO users(username, password, role) VALUES (?,?,?);";
    private static final String UPDATE = "UPDATE users SET username=?, password=?, role=? WHERE user=?;";
    private static final String SELECT_BY_USER_PASSWORD = "SELECT * FROM users WHERE username=? and password=?";
    private final DB2JDBCTemplate<User> template;
    private final Collector<User> collector = new Collector<User>() {
        @Override
        public User collect(ResultSet rs) throws SQLException {
            User user = new User();
            user.setUser(rs.getString("username"));
            user.setRole(Role.valueOf(rs.getString("role").toUpperCase()));
            return user;
        }
    };

    public DB2UserDao(DB2JDBCTemplate<User> template) {
        this.template = template;
    }

    public User getByUsernameAndPassword(String id, String password) throws DAOException {
        List<User> result = template.executeSelect(SELECT_BY_USER_PASSWORD, new Object[]{id, password}, collector);
        if (result.size() == 1) return result.get(0);
        else throw new DAOException("no such entity");
    }

    @Override
    public List<User> getList() throws DAOException {
        return template.executeSelect(SELECT_ALL, new Object[]{}, collector);
    }

    @Override
    public User findOne(String id) throws DAOException {
        List<User> result = template.executeSelect(SELECT_BY_USER, new Object[]{id}, collector);
        if (result.size() == 1) return result.get(0);
        else throw new DAOException();
    }

    @Override
    public int remove(String id) throws DAOException {
        if (template.executeUpdate(DELETE_BY_USER, new Object[]{id}) == 1) return 1;
        else throw new DAOException(format("incorrect remove user %s", id));

    }

    @Override
    public User saveOrUpdate(String oldUser, User entity) throws DAOException {
        if ("".equals(oldUser)) {
            if (template.executeUpdate(INSERT,
                    new Object[]{entity.getUser(), entity.getPassword(), entity.getRole().toString()}) == 1) {
                return entity;
            } else {
                throw new DAOException(format("incorrect save %s", entity));
            }
        } else {
            if (template.executeUpdate(UPDATE,
                    new Object[]{entity.getUser(), entity.getPassword(), entity.getRole().toString(), oldUser}) == 1) {
                return entity;
            } else {
                throw new DAOException(format("incorrect update %s", entity));
            }
        }
    }
}
