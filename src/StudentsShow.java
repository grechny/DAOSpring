import dao.DAOFactory;
import dao.GenericDAO;
import dto.Student;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;


public class StudentsShow extends HttpServlet {


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
        out.println("<head><title>Students</title></head>");
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

        out.println("<table border=\"1\">");
        out.println("<thead> <th>Student Id</th> <th>Last Name</th> <th>First Name</th> </thead>");
        out.println("<tbody>");
        try {
            for (Student students : (ArrayList<Student>) studentDAO.selectAll()) {
                out.println("<tr><td>" + students.getId() +
                        "</td><td>" + students.getLastName() + "</td><td>" + students.getFirstName() + "</td></tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("</tbody></table>");
        out.println("</body></html>");
        out.close();
    }
}
