<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Subjects Delete</title>
</head>
<body>
  <a href=/main><input type=button value="Main Page"></a><br><br>
  <%
  String subjectId = request.getParameter("subjectId");
  if (subjectId == null){ %>
    <form action=/subjects/delete method=POST>
      Subject ID: <input type=text size=20 name=subjectId><br>
      <input type=submit>
    </form>
  <% } else if (subjectId.length() > 0) {
    Boolean isDeleted = (Boolean) request.getAttribute("isDeleted");
    if (isDeleted) { %>
      Subject ID <%= subjectId %> is deleted successfully<br>
    <% } else { %>
      Unable to delete subject
    <% }
  } else { %>
    <br>Please try again
  <% } %>
</body>
</html>
