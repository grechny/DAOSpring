<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students Update</title>
</head>
<body>
  <a href=/main><input type=button value="Main Page"></a><br><br>
  <%
  String studentId = request.getParameter("studentId");
  String lastName = request.getParameter("lastName");
  String firstName = request.getParameter("firstName");

  if (studentId == null || lastName == null || firstName == null){%>
    <form action=/students/update method=POST>
      Student ID: <input type=text size=20 name=studentId><br>
      Last Name:  <input type=text size=20 name=lastName><br>
      First Name: <input type=text size=20 name=firstName><br>
      <input type=submit>
    </form>
  <% } else if (studentId.length() > 0 && lastName.length() > 0 && firstName.length() > 0) {
    Boolean isUpdated = (Boolean) request.getAttribute("isUpdated");
    if (isUpdated) { %>
      Student is updated successfully<br>
    <% } else { %>
      Unable to update student<br>
    <% } %>
    Student ID: <%= studentId %> <br>
    Last Name: <%= lastName %> <br>
    First Name: <%= firstName %>
  <% } else {%>
    Please try again
  <% } %>
</body>
</html>