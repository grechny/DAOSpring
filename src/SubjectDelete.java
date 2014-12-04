import dao.DAOFactory;
import dao.GenericDAO;
import dto.Mark;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectDelete extends HttpServlet {

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
        out.println("<head><title>Subject Delete</title></head>");
        out.println("<body>");

        out.println("<a href=\"/main\"><input type=\"button\" value=\"Main Page\"></a><br><br>");

        out.print("<form action=\"");
        out.print("/subjects/delete\"");
        out.println("method=POST>");
        out.println("Subject ID: ");
        out.println("<input type=text size=20 name=subjectId>");
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
        out.println("<head><title>Subject Delete</title></head>");
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
        GenericDAO markDAO = mysqlFactory.getMarksDAO();

        String subjectId = request.getParameter("subjectId");
        int subject_id = Integer.parseInt(subjectId);

        if (subjectId.length() > 0) {

            try {
                for (Mark marks : (ArrayList<Mark>) markDAO.selectAll()) {
                    if (marks.getSubjectId() == subject_id) {
                        markDAO.delete(marks.getId());
                    }
                }

                subjectDAO.delete(subject_id);
                out.println("Subject ID " + subject_id + " is deleted successfully<br>");

            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Unable to delete subject");
            }

        } else out.println("<br>Please, try again");

        out.println("</body></html>");
        out.close();
    }
}
