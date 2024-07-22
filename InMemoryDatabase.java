import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase {
    private final Map<Integer, Employee> employees = new HashMap<>();
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public ReentrantReadWriteLock getLock() {
        return rwLock;
    }

    public void addEmployee(Employee employee) {
        rwLock.writeLock().lock();
        try {
            employees.put(employee.getEmployeeNumber(), employee);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public Employee getEmployee(int employeeNumber) {
        rwLock.readLock().lock();
        try {
            return employees.get(employeeNumber);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void updateEmployee(int employeeNumber, Employee updatedEmployee) {
        rwLock.writeLock().lock();
        try {
            employees.put(employeeNumber, updatedEmployee);
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}
