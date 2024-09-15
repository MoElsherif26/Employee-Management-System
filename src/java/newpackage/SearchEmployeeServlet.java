package newpackage;
import jakarta.servlet.RequestDispatcher;
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

@WebServlet("/searchEmployee")
public class SearchEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchEmployeeID = request.getParameter("searchEmployeeID");
        String searchDesignation = request.getParameter("searchDesignation");

        List<Employee> searchResults;

        // If searching by Employee ID
        if (searchEmployeeID != null && !searchEmployeeID.isEmpty()) {
            searchResults = searchByEmployeeID(searchEmployeeID);
        }
        // If searching by Designation
        else if (searchDesignation != null && !searchDesignation.isEmpty()) {
            searchResults = searchByDesignation(searchDesignation);
        }
        // If neither Employee ID nor Designation is provided
        else {
            searchResults = null; // Or you can set it to an empty list
        }

        // Forward to the searchResults.jsp page
        request.setAttribute("searchResults", searchResults);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/searchResults.jsp");
        dispatcher.forward(request, response);
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

    private void displaySearchResults(HttpServletResponse response, List<Employee> searchResults) throws IOException {
        // Display search results directly on the response
        response.getWriter().write("<html><body><h2>Search Results</h2>");
        response.getWriter().write("<ul>");
        for (Employee employee : searchResults) {
            response.getWriter().write("<li>" + employee + "</li>");
        }
        response.getWriter().write("</ul></body></html>");
    }

    private List<Employee> searchByEmployeeID(String searchEmployeeID) {
        List<Employee> employees = readJsonFile();
        if (employees != null) {
            for (Employee employee : employees) {
                if (String.valueOf(employee.getEmployeeID()).equals(searchEmployeeID)) {
                    return List.of(employee); // Return a list with the matching employee
                }
            }
        }
        return List.of(); // Return an empty list if no match found
    }

    private List<Employee> searchByDesignation(String searchDesignation) {
        List<Employee> employees = readJsonFile();
        if (employees != null) {
            List<Employee> results = new ArrayList<>();
            for (Employee employee : employees) {
                if (employee.getDesignation().equalsIgnoreCase(searchDesignation)) {
                    results.add(employee);
                }
            }
            return results; // Return the list of matching employees
        }
        return List.of(); // Return an empty list if no match found
    }
}
