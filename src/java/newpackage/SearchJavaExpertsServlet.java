package newpackage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

@WebServlet("/searchJavaExperts")
public class SearchJavaExpertsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> allEmployees = readJsonFile();

        // Filter employees who know Java with a score higher than 50
        List<Employee> javaExperts = allEmployees.stream()
                .filter(employee -> hasJavaKnowledge(employee, 50))
                .sorted(Comparator.comparingInt(employee -> getJavaKnowledgeScore(employee)))
                .collect(Collectors.toList());

        // Pass the results to the result JSP page
        request.setAttribute("javaExperts", javaExperts);
        request.getRequestDispatcher("/javaExpertsResult.jsp").forward(request, response);
    }

    private boolean hasJavaKnowledge(Employee employee, int minScore) {
        return employee.getKnownLanguages().stream()
                .anyMatch(language -> language.getLanguageName().equalsIgnoreCase("Java") && language.getScoreOutof100() > minScore);
    }

    private int getJavaKnowledgeScore(Employee employee) {
        // Find and return the Java knowledge score, assuming an employee may have multiple languages
        return employee.getKnownLanguages().stream()
                .filter(language -> language.getLanguageName().equalsIgnoreCase("Java"))
                .map(ProgrammingLanguage::getScoreOutof100)
                .findFirst()
                .orElse(0); // Return 0 if not found (you may adjust this based on your logic)
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