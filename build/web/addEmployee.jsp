<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Employee</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        form {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        input[type="text"] {
            width: calc(100% - 12px);
            padding: 6px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 8px 12px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        .language-container {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 3px;
        }

        .language-container input {
            margin-bottom: 5px;
        }

        .remove-language {
            background-color: #f44336;
            color: white;
            border: none;
            padding: 5px;
            border-radius: 3px;
            cursor: pointer;
        }

        .remove-language:hover {
            background-color: #d32f2f;
        }
    </style>

    <script>
        var languageCount = 1;

        function addLanguageField() {
            languageCount++;
            var container = document.getElementById('languages-container');
            var newDiv = document.createElement('div');
            newDiv.classList.add('language-container');
            newDiv.innerHTML =
                '<label>Language Name:</label>' +
                '<input type="text" name="languageName' + languageCount + '" required>' +
                '<label>Score Out of 100:</label>' +
                '<input type="text" name="scoreOutof100' + languageCount + '" required>' +
                '<button class="remove-language" type="button" onclick="removeLanguageField(this)">Remove</button>';
            container.appendChild(newDiv);

            // Update the hidden input value
            document.getElementById('languageCount').value = languageCount;
        }

        function removeLanguageField(button) {
            languageCount--;
            var container = button.parentNode;
            container.parentNode.removeChild(container);

            // Update the hidden input value
            document.getElementById('languageCount').value = languageCount;
        }
    </script>
</head>
<body>
    <h2>Add Employee</h2>
    <form action="AddEmployeeServlet" method="post">
        <label for="firstName">First Name:</label>
        <input type="text" name="firstName" required><br>

        <label for="lastName">Last Name:</label>
        <input type="text" name="lastName" required><br>

        <label for="employeeID">Employee ID:</label>
        <input type="text" name="employeeID" required><br>

        <label for="designation">Designation:</label>
        <input type="text" name="designation" required><br>

        <!-- Fields for known languages -->
        <div id="languages-container" class="language-container">
            <label>Language Name:</label>
            <input type="text" name="languageName1" required>
            <label>Score Out of 100:</label>
            <input type="text" name="scoreOutof1001" required>
            <button class="remove-language" type="button" onclick="removeLanguageField(this)">Remove</button>
        </div>

        <button type="button" onclick="addLanguageField()">Add Language</button>

        <!-- Hidden input to track the number of known languages -->
        <input type="hidden" id="languageCount" name="languageCount" value="1">

        <input type="submit" value="Add Employee">
    </form>
</body>
</html>
