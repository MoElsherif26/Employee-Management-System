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

@WebServlet("/allEmployeesSearch")
public class AllEmployeesSearchServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> allEmployees = readJsonFile();

        // Pass the results to the result JSP page
        request.setAttribute("allEmployees", allEmployees);
        request.getRequestDispatcher("/allEmployeesResult.jsp").forward(request, response);
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
