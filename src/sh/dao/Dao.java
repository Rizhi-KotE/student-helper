package sh.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    List<T> getList() throws SQLException, ClassNotFoundException;

    T findOne(long id) throws SQLException, ClassNotFoundException;

    T update(T entity);
}
