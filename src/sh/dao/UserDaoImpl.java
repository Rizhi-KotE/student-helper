package sh.dao;

import sh.model.Role;
import sh.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class UserDaoImpl extends AbstractDAO implements UserDao {
    @Override
    public List<User> getUsers() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(format("SELECT * FROM users"));
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            String userName = rs.getString(1);
            String password = rs.getString(2);
            Role role = Role.valueOf(rs.getString(3));
            users.add(new User(userName, password, role));
        }
        rs.close();
        return users;
    }

    @Override
    public User findByUsername(String id) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(format("SELECT * FROM users WHERE user='%s'", id));
        User user = null;
        while (rs.next()) {
            String userName = rs.getString(1);
            String password = rs.getString(2);
            Role role = Role.valueOf(rs.getString(3));
            user = new User(userName, password, role);
        }
        rs.close();
        return user;
    }

    @Override
    public User update(User entity) {
        return null;
    }
}
