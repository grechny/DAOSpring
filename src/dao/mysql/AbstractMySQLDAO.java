package dao.mysql;

import dao.GenericDAO;

import java.sql.*;
import java.util.*;

public abstract class AbstractMySQLDAO<T> implements GenericDAO<T>{

    private Connection connection;

    public AbstractMySQLDAO() {
        this.connection = MySQLDAOFactory.createConnection();
    }

    public abstract String selectByIdQuery();
    public abstract String selectLastInsertIdQuery();
    public abstract String selectAllQuery();
    public abstract String updateQuery();
    public abstract String insertQuery();
    public abstract String deleteQuery();

    protected abstract ArrayList<T> parseResultSet(ResultSet rs) throws SQLException;
    protected abstract void preparedStatementForUpdate (PreparedStatement statement, T object) throws SQLException;
    protected abstract void preparedStatementForInsert (PreparedStatement statement, T object) throws SQLException;

    @Override
    public T selectByID(Integer key) throws SQLException {

        String sql = selectByIdQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, key);
        ResultSet rs = statement.executeQuery();

        ArrayList<T> list;
        list = parseResultSet(rs);

        if (list == null || list.size() == 0) {
            return null;
        }

        return list.iterator().next();
    }

    @Override
    public List<T> selectAll() throws SQLException {
        String sql = selectAllQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

        ArrayList<T> list;
        list = parseResultSet(rs);

        if (list == null || list.size() == 0) {
            return null;
        }

        return list;
    }

    @Override
    public T create(T object) throws SQLException {

        String sql = insertQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        preparedStatementForInsert(statement, object);
        statement.executeUpdate();

        sql = selectLastInsertIdQuery();
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

        ArrayList<T> list;
        list = parseResultSet(rs);

        if (list == null || list.size() == 0) throw new Error("Object hasn't been created");

        return list.iterator().next();
    }

    @Override
    public void update(T object) throws SQLException {

        String sql = updateQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        preparedStatementForUpdate (statement, object);
        statement.executeUpdate();

    }

    @Override
    public void delete(Integer key) throws SQLException {

        String sql = deleteQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, key);
        statement.executeUpdate();
        statement.close();
    }
}
