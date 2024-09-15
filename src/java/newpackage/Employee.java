package newpackage;

// Employee.java
import java.util.List;

public class Employee {
    private String firstName;
    private String lastName;
    private int employeeID;
    private String designation;
    private List<ProgrammingLanguage> knownLanguages;

    // Constructors
    public Employee() {
    }

    public Employee(String firstName, String lastName, int employeeID, String designation, List<ProgrammingLanguage> knownLanguages) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeID = employeeID;
        this.designation = designation;
        this.knownLanguages = knownLanguages;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<ProgrammingLanguage> getKnownLanguages() {
        return knownLanguages;
    }

    public void setKnownLanguages(List<ProgrammingLanguage> knownLanguages) {
        this.knownLanguages = knownLanguages;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeID=" + employeeID +
                ", designation='" + designation + '\'' +
                ", knownLanguages=" + knownLanguages +
                '}';
    }
}
