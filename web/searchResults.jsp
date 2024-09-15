<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="newpackage.Employee" %> <!-- Assuming Employee is in the default package -->
<%@ page import="newpackage.ProgrammingLanguage" %> 
<html>
<head>
    <title>Search Results</title>
</head>
<body>
    <h2>Search Results</h2>
    
    <% List<Employee> searchResults = (List<Employee>) request.getAttribute("searchResults"); %>
    
    <% if (searchResults != null && !searchResults.isEmpty()) { %>
        <ul>
            <% for (Employee employee : searchResults) { %>
                <li><%= employee.getFirstName() %> <%= employee.getLastName() %>, Employee ID: <%= employee.getEmployeeID() %>, Designation: <%= employee.getDesignation() %></li>
                <ul>
                    <% for (ProgrammingLanguage language : employee.getKnownLanguages()) { %>
                        <li><%= language.getLanguageName() %>, Score Out of 100: <%= language.getScoreOutof100() %></li>
                    <% } %>
                </ul>
            <% } %>
        </ul>
    <% } else { %>
        <p>No employees found.</p>
    <% } %>
</body>
</html>
