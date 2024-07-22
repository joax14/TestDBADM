import java.util.Scanner;

public class EmployeeApp {
    public static void main(String[] args) {
        InMemoryDatabase db = new InMemoryDatabase();
        EmployeeService service = new EmployeeService(db);

        // Populate the database with sample data
        service.createEmployee(new Employee(1, "Smith", "John", "x123", "john.smith@example.com", "Manager", "N", false, null, null));
        service.createEmployee(new Employee(2, "Doe", "Jane", "x124", "jane.doe@example.com", "Clerk", "S", false, null, null));

        // CLI interaction
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter employee number to fetch:");
        int empNumber = scanner.nextInt();
        Employee employee = service.getEmployee(empNumber);

        if (employee != null) {
            System.out.println("Employee Found: " + employee.getFirstName() + " " + employee.getLastName());
        } else {
            System.out.println("Employee not found.");
        }
    }
}
