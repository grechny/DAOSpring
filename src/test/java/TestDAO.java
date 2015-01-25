import by.grechny.webapp.dao.GenericDAO;
import by.grechny.webapp.dto.Mark;
import by.grechny.webapp.dto.Student;
import by.grechny.webapp.dto.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/spring-context.xml")
@SuppressWarnings("unused")
public class TestDAO {

    @Autowired
    private GenericDAO<Student> studentDAO;

    @Autowired
    private GenericDAO<Subject> subjectDAO;

    @Autowired
    private GenericDAO<Mark> markDAO;

    @Test
    public void testDAO() throws Exception {

        Assert.assertNotNull("Error - student DAO doesn't exist", studentDAO);
        Assert.assertNotNull("Error - subject DAO doesn't exist",subjectDAO);
        Assert.assertNotNull("Error - mark DAO doesn't exist", markDAO);

    }

    @Test
    public void testCreateDeleteStudent() throws SQLException {

        Student student = new Student();
        student.setValues(0, "FirstName", "LastName");
        Student actualStudent = studentDAO.create(student);
        student.setId(actualStudent.getId());
        Assert.assertEquals("Objects (Student) doesn't equal", student, actualStudent);

        studentDAO.delete(actualStudent);
        actualStudent = studentDAO.selectById(actualStudent.getId());
        Assert.assertNull("Object Student doesn't delete", actualStudent);
    }

    @Test
    public void testCreateDeleteSubject() throws SQLException {

        Subject subject = new Subject();
        subject.setValues(0, "Subject");
        Subject actualSubject = subjectDAO.create(subject);
        subject.setId(actualSubject.getId());
        Assert.assertEquals("Objects (Student) doesn't equal", subject, actualSubject);

        subjectDAO.delete(actualSubject);
        actualSubject = subjectDAO.selectById(actualSubject.getId());
        Assert.assertNull("Object Student doesn't delete", actualSubject);
    }

    @Test
    public void testCreateDeleteMark() throws Exception {

        Student student = new Student();
        student.setValues(0, "FirstName", "LastName");
        Student actualStudent = studentDAO.create(student);
        student.setId(actualStudent.getId());
        Assert.assertEquals("Objects (Student) doesn't equal", student, actualStudent);

        Subject subject = new Subject();
        subject.setValues(0, "Subject");
        Subject actualSubject = subjectDAO.create(subject);
        subject.setId(actualSubject.getId());
        Assert.assertEquals("Objects (Subject) doesn't equal", subject, actualSubject);

        Mark mark = new Mark();
        mark.setValues(0,actualStudent,actualSubject,10);
        Mark actualMark = markDAO.create(mark);
        mark.setId(actualMark.getId());
        Assert.assertEquals("Objects (Mark) doesn't equal",mark,actualMark);

        markDAO.delete(actualMark);
        actualMark = markDAO.selectById(actualMark.getId());
        Assert.assertNull("Object Mark doesn't delete", actualMark);
    }
}
