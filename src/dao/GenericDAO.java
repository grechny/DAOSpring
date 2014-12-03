package dao;

import java.sql.SQLException;
import java.util.List;

/**
 Обновить студента, предмет, оценка
 Добавить студента, предмет, оценку
 Удалить студента, предмет, оценку
 */

public interface GenericDAO <T> {

    public T selectByID (Integer key) throws SQLException;
    public List<T> selectAll() throws SQLException;
    public T create(T object) throws SQLException;
    public void update(T object) throws SQLException;
    public void delete(Integer key) throws SQLException;

}