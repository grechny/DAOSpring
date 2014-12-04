import dao.DAOFactory;
import dao.GenericDAO;
import dto.Subject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectsShow extends HttpServlet {


    public void doGet (HttpServletRequest request,HttpServletResponse response) {

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

        DAOFactory mysqlFactory;
        try {
            mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.Factory.MYSQL);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        GenericDAO subjectDAO = mysqlFactory.getSubjectsDAO();

        out.println("<table border=\"1\">");
        out.println("<thead> <th>Subject Id</th> <th>Subject Name</th></thead>");
        out.println("<tbody>");
        try {
            for (Subject subjects : (ArrayList<Subject>) subjectDAO.selectAll()) {
                out.println("<tr><td>" + subjects.getId() +
                        "</td><td>" + subjects.getSubject() + "</td></tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println("</tbody></table>");
        out.println("</body></html>");
        out.close();
    }
}
