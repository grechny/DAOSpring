<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Subjects Update</title>
</head>
<body>
  <a href=/main><input type=button value="Main Page"></a><br><br>
  <%
  String subjectId = request.getParameter("subjectId");
  String subjectName = request.getParameter("subjectName");

  if (subjectId == null || subjectName == null){%>
    <form action=/subjects/update method=POST>
      Subject ID: <input type=text size=20 name=subjectId><br>
      Subject Name:  <input type=text size=20 name=subjectName><br>
      <input type=submit>
    </form>
  <% } else if (subjectId.length() > 0 && subjectId.length() > 0) {
    Boolean isUpdated = (Boolean) request.getAttribute("isUpdated");
    if (isUpdated) { %>
      Subject is updated successfully<br>
    <% } else { %>
      Unable to update subject<br>
    <% } %>
    Subject ID: <%= subjectId %> <br>
    Subject Name: <%= subjectName %> <br>
  <% } else {%>
    Please try again
  <% } %>
</body>
</html>