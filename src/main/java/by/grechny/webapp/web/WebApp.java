package by.grechny.webapp.web;

import by.grechny.webapp.dao.GenericDAO;
import by.grechny.webapp.dto.Mark;
import by.grechny.webapp.dto.Student;
import by.grechny.webapp.dto.Subject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class WebApp extends HttpServlet {

    public void doGet (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        @SuppressWarnings("unchecked")
        GenericDAO<Student> studentDAO = (GenericDAO<Student>) ctx.getBean("studentsMysqlDao");
        @SuppressWarnings("unchecked")
        GenericDAO<Subject> subjectDAO = (GenericDAO<Subject>) ctx.getBean("subjectsMysqlDao");
        @SuppressWarnings("unchecked")
        GenericDAO<Mark> markDAO = (GenericDAO<Mark>) ctx.getBean("marksMysqlDao");

        Student student = new Student();
        Subject subject = new Subject();
        Mark mark = new Mark();

        ArrayList <Student> students = new ArrayList<>();
        ArrayList <Subject> subjects = new ArrayList<>();

        String studentId = request.getParameter("studentId");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String subjectId = request.getParameter("subjectId");
        String subjectName = request.getParameter("subjectName");
        String markValue = request.getParameter("markValue");

        Boolean isAdded = false;
        Boolean isUpdated = false;
        Boolean isDeleted = false;

        switch (request.getRequestURI()){
            case "/":
            case "/main":
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                break;

            case "/students":
                try {
                    students = (ArrayList<Student>) studentDAO.selectAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.setAttribute("students", students);
                getServletContext().getRequestDispatcher("/studentsList.jsp").forward(request, response);
                break;

            case "/students/update":
                if (studentId == null || lastName == null || firstName == null){
                    isUpdated = false;
                } else if (studentId.length() > 0 && lastName.length() > 0 && firstName.length() > 0) {
                    student.setValues(Integer.parseInt(studentId), firstName, lastName);
                    try {
                        studentDAO.update(student);
                        isUpdated = true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                request.setAttribute("isUpdated",isUpdated);
                getServletContext().getRequestDispatcher("/studentsUpdate.jsp").forward(request, response);
                break;

            case "/students/delete":

                if (studentId == null){
                    isDeleted = false;
                } else if (studentId.length() > 0) {
                    try {
                        student = studentDAO.selectById(Integer.parseInt(studentId));
                        studentDAO.delete(student);
                        isDeleted = true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                request.setAttribute("isDeleted",isDeleted);
                getServletContext().getRequestDispatcher("/studentsDelete.jsp").forward(request, response);
                break;

            case "/subjects":
                try {
                    subjects = (ArrayList<Subject>) subjectDAO.selectAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.setAttribute("subjects", subjects);
                getServletContext().getRequestDispatcher("/subjectsList.jsp").forward(request, response);
                break;

            case "/subjects/update":
                if (subjectId == null || subjectName == null){
                    isUpdated = false;
                } else if (subjectId.length() > 0 && subjectName.length() > 0) {
                    subject.setValues(Integer.parseInt(subjectId),subjectName);
                    try {
                        subjectDAO.update(subject);
                        isUpdated = true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                request.setAttribute("isUpdated",isUpdated);
                getServletContext().getRequestDispatcher("/subjectsUpdate.jsp").forward(request, response);
                break;

            case "/subjects/delete":
                if (subjectId == null) {
                    isDeleted = false;
                } else if (subjectId.length() > 0) {
                    try {
                        subject = subjectDAO.selectById(Integer.parseInt(subjectId));
                        subjectDAO.delete(subject);
                        isDeleted = true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                request.setAttribute("isDeleted",isDeleted);
                getServletContext().getRequestDispatcher("/subjectsDelete.jsp").forward(request, response);
                break;

            case "/marks/add":
                if (studentId == null || subjectId == null || markValue == null) {
                    isAdded = false;
                } else if (studentId.length() > 0 && subjectId.length() > 0  && markValue.length() > 0) {
                    int student_id = Integer.parseInt(studentId);
                    int subject_Id = Integer.parseInt(subjectId);
                    int mark_value = Integer.parseInt(markValue);
                    try {
                        student = studentDAO.selectById(student_id);
                        subject = subjectDAO.selectById(subject_Id);
                        mark.setValues(0,student,subject,mark_value);
                        markDAO.create(mark);
                        isAdded = true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                request.setAttribute("isAdded",isAdded);
                getServletContext().getRequestDispatcher("/marksAdd.jsp").forward(request, response);
                break;
            default:
                try {
                    response.sendError(404, "Page doesn't exist");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
