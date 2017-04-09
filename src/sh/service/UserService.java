package sh.service;

import sh.dao.UserDao;
import sh.dao.UserDaoImpl;
import sh.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserService {

    private UserDao dao = new UserDaoImpl();

    public static UserService getInstance() {
        return Singleton.instance.service;
    }

    public List<User> getUsers() {
        try {
            return dao.getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public User getUserByUsername(String username) {
        try {
            return dao.findByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new User("", "", null);
    }

    enum Singleton {
        instance;

        UserService service = new UserService();
    }
}
