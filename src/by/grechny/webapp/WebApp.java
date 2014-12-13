package by.grechny.webapp;

import by.grechny.webapp.dao.DAOFactory;
import by.grechny.webapp.dao.GenericDAO;
import by.grechny.webapp.dto.Mark;
import by.grechny.webapp.dto.Student;
import by.grechny.webapp.dto.Subject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;


public class WebApp extends HttpServlet {

    public void doGet (HttpServletRequest request,HttpServletResponse response) {

        HttpSession session = request.getSession();
        Boolean isCreatedSession = (Boolean) session.getAttribute("isCreatedSession");
        if (isCreatedSession == null) {
            isCreatedSession = false;
        }

        DAOFactory mysqlFactory;
        try {
            mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.Factory.MYSQL);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        GenericDAO studentDAO;
        GenericDAO subjectDAO;
        GenericDAO markDAO;

        if (isCreatedSession){
            studentDAO = (GenericDAO) session.getAttribute("studentDAO");
            subjectDAO = (GenericDAO) session.getAttribute("subjectDAO");
            markDAO = (GenericDAO) session.getAttribute("markDAO");
        } else {
            studentDAO = mysqlFactory.getStudentsDAO();
            subjectDAO = mysqlFactory.getSubjectsDAO();
            markDAO = mysqlFactory.getMarksDAO();
            isCreatedSession = true;
            session.setAttribute("studentDAO",studentDAO);
            session.setAttribute("subjectDAO",subjectDAO);
            session.setAttribute("markDAO",markDAO);
            session.setAttribute("isCreatedSession",isCreatedSession);
        }

        Student student = new Student();
        Subject subject = new Subject();
        Mark mark = new Mark();

        ArrayList <Student> students = new ArrayList<Student>();
        ArrayList <Subject> subjects = new ArrayList<Subject>();

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
                getMainPage(request, response);
                break;

            case "/students":
                try {
                    students = (ArrayList<Student>) studentDAO.selectAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.setAttribute("students", students);
                getStudentsList(request, response);
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
                getStudentUpdate(request, response);
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
                getStudentDelete(request, response);
                break;

            case "/subjects":
                try {
                    subjects = (ArrayList<Subject>) subjectDAO.selectAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.setAttribute("subjects", subjects);
                getSubjectsList(request, response);
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
                getSubjectUpdate(request, response);
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
                getSubjectDelete(request, response);
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
                getMarkAdd(request, response);
                break;

            default:
                try {
                    response.sendError(404, "Page doesn't exist");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
//        studentDAO.closeConnection();
//        subjectDAO.closeConnection();
//        markDAO.closeConnection();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    private void getMainPage (HttpServletRequest request,HttpServletResponse response){

        response.setContentType("text/html");
        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        out.println("<html>");
        out.println("<head><meta charset=\"UTF-8\"><title>WebApp</title></head>");
        out.println("<body>");

        out.println("<a href=\"/students\"><input type=\"button\" value=\"Students List\"></a>&nbsp;");
        out.println("<a href=\"/students/update\"><input type=\"button\" value=\"Student Update\"></a>&nbsp;");
        out.println("<a href=\"/students/delete\"><input type=\"button\" value=\"Student Delete\"></a><br><br>");

        out.println("<a href=\"/subjects\"><input type=\"button\" value=\"Subjects List\"></a>&nbsp;");
        out.println("<a href=\"/subjects/update\"><input type=\"button\" value=\"Subject Update\"></a>&nbsp;");
        out.println("<a href=\"/subjects/delete\"><input type=\"button\" value=\"Subject Delete\"></a><br><br>");

        out.println("<a href=\"/marks/add\"><input type=\"button\" value=\"Add mark\"></a>&nbsp;");

        out.println("</body></html>");
        out.close();

    }

    private void getStudentsList(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");
        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        out.println("<html>");
        out.println("<head><title>Students</title></head>");
        out.println("<body>");
        out.println("<a href=\"/main\"><input type=\"button\" value=\"Main Page\"></a><br><br>");

        out.println("<table border=\"1\">");
        out.println("<thead> <th>Student Id</th> <th>Last Name</th> <th>First Name</th> </thead>");
        out.println("<tbody>");
        for (Student students : (ArrayList<Student>) request.getAttribute("students")) {
            out.println("<tr><td>" + students.getId() +
                    "</td><td>" + students.getLastName() + "</td><td>" + students.getFirstName() + "</td></tr>");
        }
        out.println("</tbody></table>");
        out.println("</body></html>");
        out.close();

    }

    private void getStudentUpdate(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");

        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        out.println("<html>");
        out.println("<head><title>Students Update</title></head>");
        out.println("<body>");
        out.println("<a href=\"/main\"><input type=\"button\" value=\"Main Page\"></a><br><br>");

        String studentId = request.getParameter("studentId");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");

        if (studentId == null || lastName == null || firstName == null){

            out.print("<form action=\"");
            out.print("/students/update\" ");
            out.println("method=POST>");
            out.println("Student ID: ");
            out.println("<input type=text size=20 name=studentId>");
            out.println("<br>");
            out.println("Last Name:");
            out.println("<input type=text size=20 name=lastName>");
            out.println("<br>");
            out.println("First Name:");
            out.println("<input type=text size=20 name=firstName>");
            out.println("<br>");
            out.println("<input type=submit>");
            out.println("</form>");
            out.println("</body></html>");

        } else if (studentId.length() > 0 && lastName.length() > 0 && firstName.length() > 0) {

            Boolean isUpdated = (Boolean) request.getAttribute("isUpdated");
            if (isUpdated) {
                out.println("Student is updated successfully<br>");
            } else {
                out.println("Unable to update student");
                out.println("</body></html>");
                out.close();
                return;
            }

            out.println("Student ID");
            out.println(" = " + studentId + "<br>");
            out.println("Last Name:");
            out.println(" = " + lastName + "<br>");
            out.println("First Name:");
            out.println(" = " + firstName);

        } else out.println("Please try again");

        out.println("</body></html>");
        out.close();
    }

    private void getStudentDelete(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");

        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        out.println("<html>");
        out.println("<head><title>Students Delete</title></head>");
        out.println("<body>");
        out.println("<a href=\"/main\"><input type=\"button\" value=\"Main Page\"></a><br><br>");

        String studentId = request.getParameter("studentId");

        if (studentId == null){

            out.print("<form action=\"");
            out.print("/students/delete\"");
            out.println("method=POST>");
            out.println("Student ID: ");
            out.println("<input type=text size=20 name=studentId>");
            out.println("<br>");
            out.println("<input type=submit>");
            out.println("</form>");

        } else if (studentId.length() > 0) {

            Boolean isDeleted = (Boolean) request.getAttribute("isDeleted");
            if (isDeleted) {
                out.println("Student ID " + studentId + " is deleted successfully<br>");
            } else {
                out.println("Unable to delete student");
            }

        } else out.println("<br>Please try again");

        out.println("</body></html>");
        out.close();
    }

    private void getSubjectsList(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");
        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        out.println("<html>");
        out.println("<head><title>Subjects</title></head>");
        out.println("<body>");
        out.println("<a href=\"/main\"><input type=\"button\" value=\"Main Page\"></a><br><br>");
        out.println("<table border=\"1\">");
        out.println("<thead> <th>Subject Id</th> <th>Subject Name</th></thead>");
        out.println("<tbody>");
        for (Subject subjects : (ArrayList<Subject>) request.getAttribute("subjects")) {
            out.println("<tr><td>" + subjects.getId() +
                    "</td><td>" + subjects.getSubject() + "</td></tr>");
        }
        out.println("</tbody></table>");
        out.println("</body></html>");
        out.close();
    }

    private void getSubjectUpdate(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");

        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        out.println("<html>");
        out.println("<head><title>Subject Update</title></head>");
        out.println("<body>");
        out.println("<a href=\"/main\"><input type=\"button\" value=\"Main Page\"></a><br><br>");

        String subjectId = request.getParameter("subjectId");
        String subjectName = request.getParameter("subjectName");

        if (subjectId == null || subjectName == null){

            out.print("<form action=\"");
            out.print("/subjects/update\" ");
            out.println("method=POST>");
            out.println("Subject ID: ");
            out.println("<input type=text size=20 name=subjectId>");
            out.println("<br>");
            out.println("Subject Name:");
            out.println("<input type=text size=20 name=subjectName>");
            out.println("<br>");
            out.println("<input type=submit>");
            out.println("</form>");

        } else if (subjectId.length() > 0 && subjectName.length() > 0) {

            Boolean isUpdated = (Boolean) request.getAttribute("isUpdated");
            if (isUpdated) {
                out.println("Subject is updated successfully<br>");
            } else {
                out.println("Unable to update Subject");
                out.println("</body></html>");
                out.close();
                return;
            }

            out.println("Subject ID");
            out.println(" = " + subjectId + "<br>");
            out.println("Subject Name:");
            out.println(" = " + subjectName + "<br>");

        } else out.println("<br>Please, try again");

        out.println("</body></html>");
        out.close();

    }

    private void getSubjectDelete(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");

        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        out.println("<html>");
        out.println("<head><title>Subject Delete</title></head>");
        out.println("<body>");
        out.println("<a href=\"/main\"><input type=\"button\" value=\"Main Page\"></a><br><br>");

        String subjectId = request.getParameter("subjectId");

        if (subjectId == null) {

            out.print("<form action=\"");
            out.print("/subjects/delete\"");
            out.println("method=POST>");
            out.println("Subject ID: ");
            out.println("<input type=text size=20 name=subjectId>");
            out.println("<br>");
            out.println("<input type=submit>");
            out.println("</form>");

        } else if (subjectId.length() > 0) {

            Boolean isDeleted = (Boolean) request.getAttribute("isDeleted");
            if (isDeleted) {
                out.println("Subject ID " + subjectId + " is deleted successfully<br>");
            } else {
                out.println("Unable to delete subject");
            }

        } else out.println("<br>Please, try again");

        out.println("</body></html>");
        out.close();
    }

    private void getMarkAdd(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");

        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        out.println("<html>");
        out.println("<head><title>Marks Add</title></head>");
        out.println("<body>");
        out.println("<a href=\"/main\"><input type=\"button\" value=\"Main Page\"></a><br><br>");

        String studentId = request.getParameter("studentId");
        String subjectId = request.getParameter("subjectId");
        String markValue = request.getParameter("markValue");

        if (studentId == null || subjectId == null || markValue == null) {

            out.print("<form action=\"");
            out.print("/marks/add\" ");
            out.println("method=POST>");
            out.println("Student ID: ");
            out.println("<input type=text size=20 name=studentId>");
            out.println("<br>");
            out.println("Subject ID:");
            out.println("<input type=text size=20 name=subjectId>");
            out.println("<br>");
            out.println("Mark:");
            out.println("<input type=text size=20 name=markValue>");
            out.println("<br>");
            out.println("<input type=submit>");
            out.println("</form>");

        } else if (studentId.length() > 0 && subjectId.length() > 0  && markValue.length() > 0) {

            Boolean isAdded = (Boolean) request.getAttribute("isAdded");
            if (isAdded) {
                out.println("Mark is added successfully<br>");
            } else {
                out.println("Unable to add mark");
                out.println("</body></html>");
                out.close();
                return;
            }

            out.println("Student ID");
            out.println(" = " + studentId + "<br>");
            out.println("Subject ID");
            out.println(" = " + subjectId + "<br>");
            out.println("Mark is ");
            out.println(markValue);

        } else out.println("<br>Please, try again");

        out.println("</body></html>");
        out.close();
    }
}
