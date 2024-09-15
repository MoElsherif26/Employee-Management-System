package newpackage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

@WebServlet("/updateEmployeeForm")
public class UpdateEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeID = Integer.parseInt(request.getParameter("employeeID"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String designation = request.getParameter("designation");

        List<Employee> allEmployees = readJsonFile();

        // Find the employee to update
        for (Employee employee : allEmployees) {
            if (employee.getEmployeeID() == employeeID) {
                // Update employee data
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setDesignation(designation);

                // Save the updated data to the JSON file
                writeToJsonFile(allEmployees);

                // Redirect to a success page or display a message
                response.sendRedirect("successPage.jsp");
                return;
            }
        }

        // Redirect to an error page or display a message
        response.sendRedirect("errorPage.jsp");
    }

    private List<Employee> readJsonFile() {
        try {
            String path = getServletContext().getRealPath("/WEB-INF/employees.json");
            File file = new File(path);

            if (file.exists()) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(file, new TypeReference<List<Employee>>() {});
            } else {
                return null;
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
}
