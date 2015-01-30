<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students Update</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>
<script>
  $(document).ready(function() {
    $('#formStudentUpdate').submit(function (event) {
      event.preventDefault();

      var formData = $(this).serialize();

      $.ajax({
        url: '/students/update',
        data: formData,
        type: "POST",
        datatype: "html",
        success: function (data) {
          var newData = $(data).find("p");
          $("div#result").append(newData).fadeIn("slow");
          document.forms.formStudentUpdate.reset();
        }
      });
    })
  })
</script>
  <div>
    <a href=/main><input type=button value="Main Page"></a><br><br>
    <%
    String studentId = request.getParameter("studentId");
    String lastName = request.getParameter("lastName");
    String firstName = request.getParameter("firstName");

    if (studentId == null || lastName == null || firstName == null){%>
      <form id="formStudentUpdate">
        Student ID: <input type=text size=20 name=studentId><br>
        Last Name:  <input type=text size=20 name=lastName><br>
        First Name: <input type=text size=20 name=firstName><br>
        <input type=submit>
      </form>
      <div id="result" style="display:none"></div>
    <% } else if (studentId.length() > 0 && lastName.length() > 0 && firstName.length() > 0) {
      Boolean isUpdated = (Boolean) request.getAttribute("isUpdated");
      if (isUpdated) { %>
        <p>Student is updated successfully<br></p>
      <% } else { %>
        <p>Unable to update student<br></p>
      <% } %>
      <p>Student ID: <%= studentId %> <br>Last Name: <%= lastName %> <br>First Name: <%= firstName %></p>
    <% } else {%>
      <p>Please try again</p>
    <% } %>
  </div>
</body>
</html>