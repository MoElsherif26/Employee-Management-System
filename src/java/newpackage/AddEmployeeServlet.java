package newpackage;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {
    
    boolean x = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read the JSON file (if it exists)
        List<Employee> employees = readJsonFile();

        // Create a new Employee from the request parameters
        Employee newEmployee = createEmployeeFromRequest(request, employees);
        
        if (x == true)
        {
                        response.getWriter().write("<html><body><h2>Employee exists </h2></body></html>");
                        return;

        }

        
        // Add the new employee to the list
        employees.add(newEmployee);

        // Write the updated list back to the JSON file
        writeToJsonFile(employees);

        // Display success message directly on the response
        response.getWriter().write("<html><body><h2>Employee added successfully!</h2></body></html>");
    }

    private List<Employee> readJsonFile() {
        try {
            String path = getServletContext().getRealPath("/WEB-INF/employees.json");
            File file = new File(path);

            if (file.exists()) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(file, new TypeReference<List<Employee>>() {});
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading JSON file.", e);
        }
    }

    private void writeToJsonFile(List<Employee> employees) {
        try {
            String path = getServletContext().getRealPath("/WEB-INF/employees.json");
            File file = new File(path);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(file, employees);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error writing to JSON file.", e);
        }
    }

    private Employee createEmployeeFromRequest(HttpServletRequest request, List<Employee> employees) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        String designation = request.getParameter("designation");
        
        for (Employee employee : employees) {
            if (employee.getEmployeeID() == employeeID) {
                x = true;
            }
        }

        // Create a list of known languages
        List<ProgrammingLanguage> knownLanguages = new ArrayList<>();
        int languageCount = Integer.parseInt(request.getParameter("languageCount"));

        for (int i = 1; i <= languageCount; i++) {
            String languageName = request.getParameter("languageName" + i);
            int scoreOutof100 = Integer.parseInt(request.getParameter("scoreOutof100" + i));
            knownLanguages.add(new ProgrammingLanguage(languageName, scoreOutof100));
        }

        // Create the new Employee object
        return new Employee(firstName, lastName, employeeID, designation, knownLanguages);
    }
}
