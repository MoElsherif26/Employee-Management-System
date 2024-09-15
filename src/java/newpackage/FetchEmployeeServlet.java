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

@WebServlet("/fetchEmployee")
public class FetchEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String updateEmployeeID = request.getParameter("updateEmployeeID");

        List<Employee> allEmployees = readJsonFile();

        Employee employeeToUpdate = allEmployees.stream()
                .filter(employee -> String.valueOf(employee.getEmployeeID()).equals(updateEmployeeID))
                .findFirst()
                .orElse(null);

        if (employeeToUpdate != null) {
            // Pass the employee data to the update form JSP
            request.setAttribute("employeeToUpdate", employeeToUpdate);
            request.getRequestDispatcher("/updateEmployeeForm.jsp").forward(request, response);
        } else {
            // Redirect to an error page or display a message
            response.sendRedirect("errorPage.jsp");
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
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading JSON file.", e);
        }
    }
}
