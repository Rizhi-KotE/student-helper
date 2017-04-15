package sh.dao;

import sh.dao.Exception.DAOException;

import java.io.Serializable;
import java.util.List;

public interface Dao<T, S extends Serializable> {
    List<T> getList() throws DAOException;

    T findOne(S id) throws DAOException;

    int remove(S id) throws DAOException;

    T saveOrUpdate(S id, T entity) throws DAOException;
}
