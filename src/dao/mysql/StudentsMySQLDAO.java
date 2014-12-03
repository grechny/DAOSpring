package dao.mysql;

import dto.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StudentsMySQLDAO extends AbstractMySQLDAO<Student> {

    @Override
    public String selectByIdQuery() {
        return "SELECT * FROM students WHERE id = ?";
    }

    @Override
    public String selectLastInsertIdQuery() {
        return "SELECT * FROM students WHERE id = LAST_INSERT_ID()";
    }

    @Override
    public String selectAllQuery() {
        return "SELECT * FROM students";
    }

    @Override
    public String updateQuery() {
        return "UPDATE students SET first_name = ?, last_name = ? WHERE id = ?";
    }

    @Override
    public String insertQuery() {
        return "INSERT students (first_name, last_name) VALUES (?, ?)";
    }

    @Override
    public String deleteQuery() {
        return "DELETE FROM students WHERE id = ?";
    }

    @Override
    protected ArrayList<Student> parseResultSet(ResultSet rs) throws SQLException {

        ArrayList<Student> result = new ArrayList<Student>();

        while (rs.next()) {
            Student student = new Student();
            student.setValues(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
            result.add(student);
        }

        return result;
    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement statement, Student student) throws SQLException {
        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setInt(3, student.getId());
    }

    @Override
    protected void preparedStatementForInsert(PreparedStatement statement, Student student) throws SQLException {
        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
    }
}
