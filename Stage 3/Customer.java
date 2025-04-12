/**
 * Class representing a customer, extends the Person class.
 */
public class Customer extends Person {
    private static int customerIdCounter = 1; // Static counter for unique customer IDs
    private final String customerId;

    public Customer(String name, String email, String password) {
        super(name, email, password);
        this.customerId = generateUniqueCustomerId();
    }

    /**
     * Generates a unique ID for each customer.
     * @return A unique customer ID as a string.
     */
    private static synchronized String generateUniqueCustomerId() {
        return "Customer" + customerIdCounter++;
    }

    public String getCustomerId() {
        return customerId;
    }
}