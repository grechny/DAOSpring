package dao.mysql;

import dto.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectsMySQLDAO extends AbstractMySQLDAO<Subject> {

    @Override
    public String selectByIdQuery() {
        return "SELECT * FROM subjects WHERE id = ?";
    }

    @Override
    public String selectLastInsertIdQuery() {
        return "SELECT * FROM subjects WHERE id = LAST_INSERT_ID()";
    }

    @Override
    public String selectAllQuery() {
        return "SELECT * FROM subjects";
    }

    @Override
    public String updateQuery() {
        return "UPDATE subjects SET subject = ? WHERE id = ?";
    }

    @Override
    public String insertQuery() {
        return "INSERT subjects (subject) VALUES (?)";
    }

    @Override
    public String deleteQuery() {
        return "DELETE FROM subjects WHERE id = ?";
    }

    @Override
    protected ArrayList<Subject> parseResultSet(ResultSet rs) throws SQLException {

        ArrayList<Subject> result = new ArrayList<Subject>();

        while (rs.next()) {
            Subject subject = new Subject();
            subject.setValues(rs.getInt("id"), rs.getString("subject"));
            result.add(subject);
        }

        return result;

    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement statement, Subject subject) throws SQLException {
        statement.setString(1, subject.getSubject());
        statement.setInt(2, subject.getId());
    }

    @Override
    protected void preparedStatementForInsert(PreparedStatement statement, Subject subject) throws SQLException {
        statement.setString(1, subject.getSubject());
    }
}
