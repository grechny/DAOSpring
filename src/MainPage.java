import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class MainPage extends HttpServlet {


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
}
