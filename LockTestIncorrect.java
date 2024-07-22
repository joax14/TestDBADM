import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class LockTestIncorrect {
    public static void main(String[] args) {
        InMemoryDatabase db = new InMemoryDatabase();
        db.addEmployee(new Employee(1, "Smith", "John", "x123", "john@example.com", "Manager", "N", false, null, null));

        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Thread for updating an employee with a longer delay to simulate contention
        Runnable updateTask = () -> {
            System.out.println("Attempting to update employee...");
            try {
                db.getLock().writeLock().lock();
                try {
                    Thread.sleep(5000);  // Holding the lock for 5 seconds
                    db.updateEmployee(1, new Employee(1, "Smith", "Johnny", "x124", "johnny@example.com", "Manager", "N", false, "system", "update"));
                } finally {
                    db.getLock().writeLock().unlock();
                }
                System.out.println("Employee updated.");
            } catch (InterruptedException e) {
                System.out.println("Update task was interrupted.");
            }
        };

        // Thread for reading an employee with a timeout on the lock
        Runnable readTask = () -> {
            System.out.println("Attempting to read employee...");
            try {
                boolean lockAcquired = db.getLock().readLock().tryLock(1, TimeUnit.SECONDS);
                if (lockAcquired) {
                    try {
                        Employee emp = db.getEmployee(1);
                        System.out.println("Read Employee: " + emp.getFirstName() + " " + emp.getEmail());
                    } finally {
                        db.getLock().readLock().unlock();
                    }
                } else {
                    System.out.println("Failed to acquire read lock: Task timed out.");
                }
            } catch (InterruptedException e) {
                System.out.println("Read task was interrupted.");
            }
        };

        executor.execute(updateTask);
        executor.execute(readTask);

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted.");
        } finally {
            System.out.println("Both tasks complete.");
        }
    }
}
