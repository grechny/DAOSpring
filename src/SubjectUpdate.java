import dao.DAOFactory;
import dao.GenericDAO;
import dto.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class SubjectUpdate extends HttpServlet {

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
        out.println("<head><title>Subject Update</title></head>");
        out.println("<body>");

        out.println("<a href=\"/main\"><input type=\"button\" value=\"Main Page\"></a><br><br>");

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

        out.println("</body></html>");
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
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

        DAOFactory mysqlFactory;
        try {
            mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.Factory.MYSQL);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        GenericDAO subjectDAO = mysqlFactory.getSubjectsDAO();
        Subject subject = new Subject();

        String subjectId = request.getParameter("subjectId");
        String subjectName = request.getParameter("subjectName");

        if (subjectId.length() > 0 && subjectName.length() > 0) {

            int subject_id = Integer.parseInt(subjectId);
            subject.setValues(subject_id,subjectName);
            try {
                subjectDAO.update(subject);
                out.println("Subject is updated successfully<br>");
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Unable to update Subject");
            }

            out.println("Subject ID");
            out.println(" = " + subject_id + "<br>");
            out.println("Subject Name:");
            out.println(" = " + subjectName + "<br>");

        } else out.println("<br>Please, try again");

        out.println("</body></html>");
        out.close();
    }
}
