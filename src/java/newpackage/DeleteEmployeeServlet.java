package newpackage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deleteEmployeeID = request.getParameter("deleteEmployeeID");

        List<Employee> employees = readJsonFile();

        boolean deleted = false;

        // Iterate through the employees to find and remove the specified employee by ID
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (String.valueOf(employee.getEmployeeID()).equals(deleteEmployeeID)) {
                iterator.remove();
                deleted = true;
                break;
            }
        }

        // Write the updated list back to the JSON file
        writeToJsonFile(employees);

        // Display success or failure message directly on the response
        if (deleted) {
            response.getWriter().write("<html><body><h2>Employee with ID " + deleteEmployeeID + " deleted successfully!</h2></body></html>");
        } else {
            response.getWriter().write("<html><body><h2>Employee with ID " + deleteEmployeeID + " not found or deletion unsuccessful.</h2></body></html>");
        }
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
}
