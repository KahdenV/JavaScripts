import java.util.List;

/**
 * Service for authenticating users (customers and staff).
 */
public class AuthenticationService {
    private List<Customer> customers;
    private List<Staff> staff;

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
     * Adds a new customer to the list of customers.
     *
     * @param newCustomer The new customer to add.
     */
    public void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
    }
}