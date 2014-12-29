<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students Delete</title>
</head>
<body>
  <a href=/main><input type=button value="Main Page"></a><br><br>
  <%
  String studentId = request.getParameter("studentId");
  if (studentId == null){ %>
    <form action=/students/delete method=POST>
      Student ID: <input type=text size=20 name=studentId><br>
      <input type=submit>
    </form>
  <% } else if (studentId.length() > 0) {
    Boolean isDeleted = (Boolean) request.getAttribute("isDeleted");
    if (isDeleted) { %>
      Student ID <%= studentId %> is deleted successfully<br>
    <% } else { %>
      Unable to delete student
    <% }
  } else { %>
    <br>Please try again
  <% } %>
</body>
</html>
