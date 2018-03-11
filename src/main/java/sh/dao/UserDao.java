package sh.dao;

import sh.dao.Exception.DAOException;
import sh.model.User;

public interface UserDao extends Dao<User, String> {

    User getByUsernameAndPassword(String username, String password) throws DAOException;
}
