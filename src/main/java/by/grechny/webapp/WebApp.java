package by.grechny.webapp;

import by.grechny.webapp.dao.GenericDAO;
import by.grechny.webapp.dto.Mark;
import by.grechny.webapp.dto.Student;
import by.grechny.webapp.dto.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class WebApp extends HttpServlet {

    public void doGet (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        GenericDAO studentDAO = (GenericDAO) session.getAttribute("studentDAO");
        GenericDAO subjectDAO = (GenericDAO) session.getAttribute("subjectDAO");
        GenericDAO markDAO = (GenericDAO) session.getAttribute("markDAO");

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
                        for (Mark marks : (ArrayList<Mark>) markDAO.selectAll()) {
                            if (marks.getStudentId() == Integer.parseInt(studentId)) {
                                markDAO.delete(marks.getId());
                            }
                        }
                        studentDAO.delete(Integer.parseInt(studentId));
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
                        for (Mark marks : (ArrayList<Mark>) markDAO.selectAll()) {
                            if (marks.getSubjectId() == Integer.parseInt(subjectId)) {
                                markDAO.delete(marks.getId());
                            }
                        }
                        subjectDAO.delete(Integer.parseInt(subjectId));
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
                    mark.setValues(0,student_id,subject_Id,mark_value);
                    try {
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
