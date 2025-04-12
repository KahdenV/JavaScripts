/**
 * Class representing a person with basic information.
 */
public class Person {
    private static int idCounter = 1; 
    private String name;
    private String email;
    private String password;

    // Constructor
    public Person(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Synchronized method for generating unique IDs
    protected static synchronized String generateUniqueId() {
        return "Customer" + (idCounter++); 
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}