import java.util.List;

public class AuthenticationService {
    private List<Customer> customers;
    private List<Staff> staff;
    private int guestCounter = 1; // Counter to track unique guest IDs

    public AuthenticationService(List<Customer> customers, List<Staff> staff) {
        this.customers = customers;
        this.staff = staff;
    }

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

    public Guest loginAsGuest() {
        String guestId = "guest" + guestCounter++; // Generate a unique guest ID
        return new Guest(guestId);
    }

    public void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}