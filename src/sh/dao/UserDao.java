package sh.dao;

import sh.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> getUsers() throws SQLException, ClassNotFoundException;

    User findByUsername(String username) throws SQLException, ClassNotFoundException;

    User update(User user);
}
