import by.grechny.webapp.dao.DAOFactory;
import by.grechny.webapp.dao.GenericDAO;
import by.grechny.webapp.dao.mysql.MySQLDAOFactory;
import by.grechny.webapp.dto.Mark;
import by.grechny.webapp.dto.Student;
import by.grechny.webapp.dto.Subject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

@SuppressWarnings({"unused","unchecked"})
public class TestDAO {

    public static DAOFactory mysqlFactory;

    public static GenericDAO studentDAO;
    public static GenericDAO subjectDAO;
    public static GenericDAO markDAO;

    @BeforeClass
    public static void setUp() throws Exception {

        mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.Factory.MYSQL);

        studentDAO = mysqlFactory.getStudentsDAO();
        subjectDAO = mysqlFactory.getSubjectsDAO();
        markDAO = mysqlFactory.getMarksDAO();
    }

    @AfterClass
    public static void destroyDAO(){
        MySQLDAOFactory.closeConnection();
    }

    @Test
    public void testDAO() throws Exception {

        Assert.assertNotNull("Error - DAO Factory doesn't exist",mysqlFactory);
        Assert.assertNotNull("Error - student DAO doesn't exist", studentDAO);
        Assert.assertNotNull("Error - subject DAO doesn't exist",subjectDAO);
        Assert.assertNotNull("Error - mark DAO doesn't exist", markDAO);

    }

    @Test
    public void testCreateDeleteStudent() throws SQLException {

        Student student = new Student();
        student.setValues(0, "FirstName", "LastName");
        Student actualStudent = (Student) studentDAO.create(student);
        student.setId(actualStudent.getId());
        Assert.assertEquals("Objects (Student) doesn't equal", student, actualStudent);

        studentDAO.delete(actualStudent);
        actualStudent = (Student) studentDAO.selectById(actualStudent.getId());
        Assert.assertNull("Object Student doesn't delete", actualStudent);
    }

    @Test
    public void testCreateDeleteSubject() throws SQLException {

        Subject subject = new Subject();
        subject.setValues(0, "Subject");
        Subject actualSubject = (Subject) studentDAO.create(subject);
        subject.setId(actualSubject.getId());
        Assert.assertEquals("Objects (Student) doesn't equal", subject, actualSubject);

        studentDAO.delete(actualSubject);
        actualSubject = (Subject) studentDAO.selectById(actualSubject.getId());
        Assert.assertNull("Object Student doesn't delete", actualSubject);
    }

    @Test
    public void testCreateDeleteMark() throws Exception {

        Student student = new Student();
        student.setValues(0, "FirstName", "LastName");
        Student actualStudent = (Student) studentDAO.create(student);
        student.setId(actualStudent.getId());
        Assert.assertEquals("Objects (Student) doesn't equal", student, actualStudent);

        Subject subject = new Subject();
        subject.setValues(0, "Subject");
        Subject actualSubject = (Subject) subjectDAO.create(subject);
        subject.setId(actualSubject.getId());
        Assert.assertEquals("Objects (Subject) doesn't equal", subject, actualSubject);

        Mark mark = new Mark();
        mark.setValues(0,actualStudent,actualSubject,10);
        Mark actualMark = (Mark) markDAO.create(mark);
        mark.setId(actualMark.getId());
        Assert.assertEquals("Objects (Mark) doesn't equal",mark,actualMark);

        markDAO.delete(actualMark);
        actualMark = (Mark) markDAO.selectById(actualMark.getId());
        Assert.assertNull("Object Mark doesn't delete", actualMark);
    }
}
