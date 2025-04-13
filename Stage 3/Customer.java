public class Customer extends Person {
    private String email;
    private String password;
    private String id; // Unique ID for the customer

    // Add this constructor
    public Customer(String id, String name, String email, String password) {
        super(id); // Call the parent constructor to set the ID
        this.email = email;
        this.password = password;
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