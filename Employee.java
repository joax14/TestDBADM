public class Employee {
    private int employeeNumber;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String jobTitle;
    private String employeeType;
    private boolean isDeactivated;
    private String endUsername;
    private String endUserreason;

    public Employee(int employeeNumber, String lastName, String firstName, String extension, String email, 
                    String jobTitle, String employeeType, boolean isDeactivated, String endUsername, String endUserreason) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.extension = extension;
        this.email = email;
        this.jobTitle = jobTitle;
        this.employeeType = employeeType;
        this.isDeactivated = isDeactivated;
        this.endUsername = endUsername;
        this.endUserreason = endUserreason;
    }

    // Getters and setters
    public int getEmployeeNumber() { return employeeNumber; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getExtension() { return extension; }
    public String getEmail() { return email; }
    public String getJobTitle() { return jobTitle; }
    public String getEmployeeType() { return employeeType; }
    public boolean isDeactivated() { return isDeactivated; }
    public String getEndUsername() { return endUsername; }
    public String getEndUserreason() { return endUserreason; }
}
