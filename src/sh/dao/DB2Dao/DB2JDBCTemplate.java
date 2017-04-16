package sh.dao.DB2Dao;

import sh.dao.Collector;
import sh.dao.Exception.DAOException;

import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB2JDBCTemplate<T> {

    public List<T> executeSelect(String query, Object[] params, Collector<T> collector) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            // TODO
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            rs = statement.executeQuery();
            List<T> list = new ArrayList<T>();
            while (rs.next()) {
                list.add(collector.collect(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (NamingException e) {
            throw new DAOException(e);
        } catch (ClassNotFoundException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Connection getConnection() throws SQLException, NamingException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/student_helper?useUnicode=true&characterEncoding=utf8", "root", "1");
    }

    public int executeUpdate(String query, Object[] params) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            // TODO
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (NamingException e) {
            throw new DAOException(e);
        } catch (ClassNotFoundException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Object[]> executeAndReturnKey(String query, Object[] params, String[] generated) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            List<Object[]> result = new ArrayList<Object[]>();
            while (rs.next()) {
                Object[] keys = new Object[generated.length];
                for (int i = 0; i < generated.length; i++) {
                    keys[i] = rs.getObject(i+1);
                }
                result.add(keys);
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (NamingException e) {
            throw new DAOException(e);
        } catch (ClassNotFoundException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
