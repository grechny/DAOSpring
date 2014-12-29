<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Marks Add</title>
</head>
<body>
  <a href=/main><input type=button value="Main Page"></a><br><br>
  <%
  String studentId = request.getParameter("studentId");
  String subjectId = request.getParameter("subjectId");
  String markValue = request.getParameter("markValue");
  if (studentId == null || subjectId == null || markValue == null) { %>
    <form action=/marks/add method=POST>
      Student ID: <input type=text size=20 name=studentId><br>
      Subject ID: <input type=text size=20 name=subjectId><br>
      Mark:       <input type=text size=20 name=markValue><br>
      <input type=submit>
    </form>
  <% } else if (studentId.length() > 0 && subjectId.length() > 0  && markValue.length() > 0) {
    Boolean isAdded = (Boolean) request.getAttribute("isAdded");
    if (isAdded) { %>
      Mark is added successfully<br>
    <% } else { %>
      Unable to add mark
    <% } %>
    Student ID: <%= studentId %> <br>
    Subject ID: <%= subjectId %> <br>
    Mark is <%= markValue %>
  <% } else { %>
    <br>Please, try again
  <% } %>
</body>
</html>
