<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="newpackage.Employee" %>
<%@ page import="newpackage.ProgrammingLanguage" %>
<html>
<head>
    <title>All Employees Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            margin-bottom: 10px;
        }

        ul.sub-list {
            padding-left: 20px;
        }

        p {
            text-align: center;
            color: #888;
        }
    </style>
</head>
<body>
    <h2>All Employees Result</h2>
    
    <% List<Employee> allEmployees = (List<Employee>) request.getAttribute("allEmployees"); %>
    
    <% if (allEmployees != null && !allEmployees.isEmpty()) { %>
        <ul>
            <% for (Employee employee : allEmployees) { %>
                <li>
                    <%= employee.getFirstName() %> <%= employee.getLastName() %>,
                    Employee ID: <%= employee.getEmployeeID() %>,
                    Designation: <%= employee.getDesignation() %>
                    <ul class="sub-list">
                        <% for (ProgrammingLanguage language : employee.getKnownLanguages()) { %>
                            <li>
                                <%= language.getLanguageName() %>,
                                Score Out of 100: <%= language.getScoreOutof100() %>
                            </li>
                        <% } %>
                    </ul>
                </li>
            <% } %>
        </ul>
    <% } else { %>
        <p>No employees found.</p>
    <% } %>
</body>
</html>
