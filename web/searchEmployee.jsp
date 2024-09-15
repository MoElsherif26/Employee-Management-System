<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Employee</title>
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
            background-color: #2196F3;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 3px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0b7dda;
        }
    </style>
</head>
<body>
    <h2>Search Employee</h2>
    <form action="searchEmployee" method="post">
        <label for="searchEmployeeID">Search by Employee ID:</label>
        <input type="text" id="searchEmployeeID" name="searchEmployeeID">
        <br>
        <strong>- OR -</strong>
        <br>
        <label for="searchDesignation">Search by Designation:</label>
        <input type="text" id="searchDesignation" name="searchDesignation">
        <br>
        <input type="submit" value="Search">
    </form>
</body>
</html>
