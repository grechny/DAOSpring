<%@ page import="by.grechny.webapp.dto.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
  <a href=/main><input type=button value="Main Page"></a><br><br>
    <table border=1>
      <thead align="center">
        <tr>
          <td><b>Student Id</b></td>
          <td><b>Last Name</b></td>
          <td><b>First Name</b></td>
        </tr>
      </thead>
      <tbody>
        <% for (Student students : (ArrayList<Student>) request.getAttribute("students")) { %>
        <tr>
          <td><%= students.getId() %></td>
          <td><%= students.getLastName() %></td>
          <td><%= students.getFirstName() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</body>
</html>