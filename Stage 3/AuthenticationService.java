import java.util.List;

/**
 * Service for authenticating users (customers and staff).
 */
public class AuthenticationService {
    private List<Customer> customers;
    private List<Staff> staff;
    private int guestCounter = 1;

    public AuthenticationService(List<Customer> customers, List<Staff> staff) {
        this.customers = customers;
        this.staff = staff;
    }

    /**
     * Authenticates a user by username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The authenticated user, or null if authentication fails.
     */
    public Person authenticate(String username, String password) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(username) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        for (Staff staffMember : staff) {
            if (staffMember.getEmail().equals(username) && staffMember.getPassword().equals(password)) {
                return staffMember;
            }
        }
        return null; // Authentication failed
    }

    /**
     * Logs in a guest user.
     * @return A new Guest object.
     */
    public Guest loginAsGuest() {
        String guestId = "guest" + guestCounter++; // Generate a unique guest ID
        return new Guest(guestId);
    }

    /**
     * Adds a new customer to the list of customers.
     *
     * @param newCustomer The new customer to add.
     */
    public void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
    }

    /**
     * Retrieves the list of customers.
     *
     * @return A list of all customers.
     */
    public List<Customer> getCustomers() {
        return customers;
    }
}