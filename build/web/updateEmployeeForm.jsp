<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="newpackage.Employee" %>
<%@ page import="newpackage.ProgrammingLanguage" %>
<html>
<head>
    <title>Update Employee Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        form {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        input[type="text"] {
            width: calc(100% - 12px);
            padding: 8px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 3px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Update Employee</h2>
    
    <% Employee employeeToUpdate = (Employee) request.getAttribute("employeeToUpdate"); %>
    
    <form action="UpdateEmployeeServlet" method="post">
        <label for="employeeID">Employee ID:</label>
        <input type="text" id="employeeID" name="employeeID" value="<%= employeeToUpdate.getEmployeeID() %>" readonly><br>
        
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" value="<%= employeeToUpdate.getFirstName() %>" required><br>
        
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="<%= employeeToUpdate.getLastName() %>" required><br>
        
        <label for="designation">Designation:</label>
        <input type="text" id="designation" name="designation" value="<%= employeeToUpdate.getDesignation() %>" required><br>

        <input type="submit" value="Update Employee">
    </form>
</body>
</html>
