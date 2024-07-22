public class EmployeeService {
    private InMemoryDatabase db;

    public EmployeeService(InMemoryDatabase db) {
        this.db = db;
    }

    public void createEmployee(Employee employee) {
        db.addEmployee(employee);
    }

    public Employee getEmployee(int employeeNumber) {
        return db.getEmployee(employeeNumber);
    }

    public void updateEmployee(Employee employee) {
        db.updateEmployee(employee.getEmployeeNumber(), employee);
    }
}
