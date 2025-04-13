public class Customer extends Person {
    private String id; // Unique ID for the customer

    public Customer(String name, String email, String password, String id) {
        super(name, email, password);
        this.id = id;
    }

    /**
     * Retrieves the customer's unique ID.
     *
     * @return The customer's ID.
     */
    public String getCustomerId() {
        return id;
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + getName() + ", Email: " + getEmail();
    }
}