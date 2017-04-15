package sh.dao;

import sh.dao.Exception.DAOException;
import sh.model.User;

public interface UserDao {

    User getByUsernameAndPassword(String username, String password) throws DAOException;
}
