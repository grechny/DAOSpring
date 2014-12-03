package dao.mysql;

import dto.Mark;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarksMySQLDAO extends AbstractMySQLDAO<Mark> {

    @Override
    public String selectByIdQuery() {
        return "SELECT * FROM marks WHERE mark_id = ?";
    }

    @Override
    public String selectLastInsertIdQuery() {
        return "SELECT * FROM marks WHERE mark_id = LAST_INSERT_ID()";
    }

    @Override
    public String selectAllQuery() {
        return "SELECT * FROM marks";
    }

    @Override
    public String updateQuery() {
        return "UPDATE marks SET student_id = ?, subject_id = ?, mark = ? WHERE mark_id = ?";
    }

    @Override
    public String insertQuery() {
        return "INSERT marks (student_id, subject_id, mark) VALUES (?, ?, ?)";
    }

    @Override
    public String deleteQuery() {
        return "DELETE FROM marks WHERE mark_id = ?";
    }

    @Override
    protected ArrayList<Mark> parseResultSet(ResultSet rs) throws SQLException {

        ArrayList<Mark> result = new ArrayList<Mark>();

        while (rs.next()) {
            Mark mark = new Mark();
            mark.setValues(rs.getInt("mark_id"), rs.getInt("student_id"), rs.getInt("subject_id"), rs.getInt("mark"));
            result.add(mark);
        }

        return result;

    }

    @Override
    protected void preparedStatementForUpdate(PreparedStatement statement, Mark mark) throws SQLException {
        statement.setInt(1, mark.getStudentId());
        statement.setInt(2, mark.getSubjectId());
        statement.setInt(3, mark.getMark());
        statement.setInt(4, mark.getId());
    }

    @Override
    protected void preparedStatementForInsert(PreparedStatement statement, Mark mark) throws SQLException {
        statement.setInt(1, mark.getStudentId());
        statement.setInt(2, mark.getSubjectId());
        statement.setInt(3, mark.getMark());
    }
}
