import by.grechny.webapp.dao.DAOFactory;
import by.grechny.webapp.dao.GenericDAO;
import by.grechny.webapp.dto.Mark;
import by.grechny.webapp.dto.Student;
import by.grechny.webapp.dto.Subject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

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

    @Test
    public void testDAO() throws Exception {

        Assert.assertNotNull("Error - DAO Factory doesn't exist",mysqlFactory);
        Assert.assertNotNull("Error - student DAO doesn't exist",studentDAO);
        Assert.assertNotNull("Error - subject DAO doesn't exist",subjectDAO);
        Assert.assertNotNull("Error - mark DAO doesn't exist",markDAO);

    }

    @Test
    public void testCRUD() throws SQLException {

        Student student = new Student();
        Student actualStudent;
        student.setValues(0, "FirstName", "LastName");
        actualStudent = (Student) studentDAO.create(student);

        Subject subject = new Subject();
        Subject actualSubject;
        subject.setValues(0,"Subject");
        actualSubject = (Subject) subjectDAO.create(subject);

        Mark mark = new Mark();
        Mark actualMark;
        mark.setValues(0,actualStudent,actualSubject,10);
        actualMark = (Mark) markDAO.create(mark);

        Assert.assertNotNull(actualStudent);
        Assert.assertNotNull(actualSubject);
        Assert.assertNotNull(actualMark);

        student.setValues(actualStudent.getId(),"NewFirstName","NewLastName");
        subject.setValues(actualSubject.getId(),"NewSubject");
        mark.setValues(actualMark.getId(),actualStudent,actualSubject,0);

        markDAO.update(mark);
        studentDAO.update(student);
        subjectDAO.update(subject);

        actualStudent = (Student) studentDAO.selectById(student.getId());
        actualSubject = (Subject) subjectDAO.selectById(subject.getId());
        actualMark = (Mark) markDAO.selectById(mark.getId());

        Assert.assertEquals("Objects (Student) doesn't equal",student,actualStudent);
        Assert.assertEquals("Objects (Subject) doesn't equal",subject,actualSubject);
        Assert.assertEquals("Objects (Mark) doesn't equal",mark,actualMark);

        studentDAO.delete(actualStudent);
        subjectDAO.delete(subject);

        actualStudent = (Student) studentDAO.selectById(student.getId());
        actualSubject = (Subject) subjectDAO.selectById(subject.getId());
        actualMark = (Mark) markDAO.selectById(mark.getId());

        Assert.assertNull("Object Student doesn't delete",actualStudent);
        Assert.assertNull("Object Subject doesn't delete",actualSubject);
        Assert.assertNull("Object Mark doesn't delete",actualMark);
    }

    @AfterClass
    public static void destroyDAO(){

        studentDAO.closeConnection();
        subjectDAO.closeConnection();
        markDAO.closeConnection();

    }
}
