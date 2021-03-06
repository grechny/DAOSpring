
package by.grechny.webapp.dao.mysql;

import by.grechny.webapp.dto.Subject;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository(value = "subjectsMysqlDao")
@SuppressWarnings("unused")
public class SubjectsMySQLDAO extends AbstractMySQLDAO<Subject> {

    @Override
    public String selectAllQuery() {
        return "SELECT s FROM Subject s";
    }

    @Override
    public Subject selectById(Integer id) throws SQLException {
        em.getTransaction().begin();
        Subject mark = em.find(Subject.class, id);
        em.getTransaction().commit();
        return mark;
    }
}
