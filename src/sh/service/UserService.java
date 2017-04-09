package sh.service;

import sh.model.Role;
import sh.model.Student;
import sh.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static sh.model.Role.ADMIN;
import static sh.model.Role.PROFESSOR;
import static sh.model.Role.STUDENT;


public class UserService {

    private final Map<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
        users.put("admin", new User("admin", "1", ADMIN));
        users.put("student", new User("student", "1", STUDENT));
        users.put("professor", new User("professor", "1", PROFESSOR));
    }

    public static UserService getInstance() {
        return Singleton.instance.service;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }

    enum Singleton {
        instance;

        UserService service = new UserService();
    }
}
