package by.grechny.webapp.dao.mysql;

import by.grechny.webapp.dto.Student;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository(value = "studentsMysqlDao")
@SuppressWarnings("unused")
public class StudentsMySQLDAO extends AbstractMySQLDAO<Student> {

    @Override
    public String selectAllQuery() {
        return "SELECT s FROM Student s";
    }

    @Override
    public Student selectById(Integer id) throws SQLException {
        em.getTransaction().begin();
        Student student = em.find(Student.class, id);
        em.getTransaction().commit();
        return student;
    }
}
