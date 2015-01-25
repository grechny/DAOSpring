package by.grechny.webapp.dao.mysql;

import by.grechny.webapp.dto.Mark;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository(value = "marksMysqlDao")
@SuppressWarnings("unused")
public class MarksMySQLDAO extends AbstractMySQLDAO<Mark> {

    @Override
    public String selectAllQuery() {
        return "SELECT m FROM Mark m";
    }

    @Override
    public Mark selectById(Integer id) throws SQLException {
        em.getTransaction().begin();
        Mark mark = em.find(Mark.class, id);
        em.getTransaction().commit();
        return mark;
    }
}
