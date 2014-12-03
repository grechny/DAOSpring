import dao.DAOFactory;
import dao.GenericDAO;
import dto.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class StudentUpdate extends HttpServlet {

    public void doGet (HttpServletRequest request,HttpServletResponse response) {

        response.setContentType("text/html");

        PrintWriter out = null;
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
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        response.setContentType("text/html");

        PrintWriter out = null;
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

        DAOFactory mysqlFactory;
        try {
            mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        GenericDAO studentDAO = mysqlFactory.getStudentsDAO();
        Student student = new Student();

        String studentId = request.getParameter("studentId");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");

        if (studentId.length() > 0 && lastName.length() > 0  && firstName.length() > 0) {

            int student_id = Integer.parseInt(studentId);
            student.setValues(student_id,firstName,lastName);
            try {
                studentDAO.update(student);
                out.println("Student is updated successfully<br>");
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Unable to update student");
            }

            out.println("Student ID");
            out.println(" = " + student_id + "<br>");
            out.println("Last Name:");
            out.println(" = " + lastName + "<br>");
            out.println("First Name:");
            out.println(" = " + firstName);

        } else out.println("<br>Please, try again");

        out.println("</body></html>");
        out.close();
    }
}
