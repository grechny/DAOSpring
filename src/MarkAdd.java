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

public class MarkAdd extends HttpServlet {

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
        out.println("<head><title>Marks Add</title></head>");
        out.println("<body>");

        out.println("<a href=\"/main\"><input type=\"button\" value=\"Main Page\"></a><br><br>");

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
        out.println("<head><title>Marks Add</title></head>");
        out.println("<body>");

        out.println("<a href=\"/main\"><input type=\"button\" value=\"Main Page\"></a><br><br>");

        DAOFactory mysqlFactory;
        try {
            mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        GenericDAO markDAO = mysqlFactory.getMarksDAO();
        Mark marks = new Mark();

        String studentId = request.getParameter("studentId");
        String subjectId = request.getParameter("subjectId");
        String markValue = request.getParameter("markValue");

        if (studentId.length() > 0 && subjectId.length() > 0  && markValue.length() > 0) {

            int student_id = Integer.parseInt(studentId);
            int subject_Id = Integer.parseInt(subjectId);
            int mark_value = Integer.parseInt(markValue);
            marks.setValues(0,student_id,subject_Id,mark_value);
            try {
                markDAO.create(marks);
                out.println("Mark is added successfully<br>");
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Unable to add mark");
            }

            out.println("Student ID");
            out.println(" = " + student_id + "<br>");
            out.println("Subject ID");
            out.println(" = " + subject_Id + "<br>");
            out.println("Mark is ");
            out.println(mark_value);

        } else out.println("<br>Please, try again");

        out.println("</body></html>");
        out.close();
    }
}
