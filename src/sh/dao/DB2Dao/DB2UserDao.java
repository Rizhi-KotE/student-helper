package sh.dao.DB2Dao;

import sh.dao.Collector;
import sh.dao.Exception.DAOException;
import sh.dao.UserDao;
import sh.model.Role;
import sh.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DB2UserDao implements UserDao {

    private static final String SELECT_BY_USER_PASSWORD = "SELECT * FROM users WHERE user=? and password=?";

    private final DB2JDBCTemplate<User> template;
    private Collector<User> collector = new Collector<User>() {
        @Override
        public User collect(ResultSet rs) throws SQLException {
            User user = new User();
            user.setUser(rs.getString("user"));
            user.setRole(Role.valueOf(rs.getString("role").toUpperCase()) );
            return user;
        }
    };

    public DB2UserDao(DB2JDBCTemplate<User> template) {
        this.template = template;
    }

    public User getByUsernameAndPassword(String id, String password) throws DAOException {
        List<User> result = template.executeSelect(SELECT_BY_USER_PASSWORD, new Object[]{id, password}, collector);
        if (result.size() == 1) return result.get(0);
        else throw new DAOException();
    }
}
