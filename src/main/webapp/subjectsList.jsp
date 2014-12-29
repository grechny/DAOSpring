<%@ page import="by.grechny.webapp.dto.Subject" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Subjects</title>
</head>
<body>
  <a href=/main><input type=button value="Main Page"></a><br><br>
    <table border=1>
      <thead align="center">
        <tr>
          <td><b>Subject Id</b></td>
          <td><b>Subject Name</b></td>
        </tr>
      </thead>
      <tbody>
        <% for (Subject subject : (ArrayList<Subject>) request.getAttribute("subjects")) { %>
        <tr>
          <td><%= subject.getId() %></td>
          <td><%= subject.getSubject() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</body>
</html>