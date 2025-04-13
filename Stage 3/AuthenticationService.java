import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {
    private List<Customer> customers;
    private List<Staff> staff;
    private int guestCounter = 1; // Counter to track unique guest IDs

    public AuthenticationService(List<Customer> customers, List<Staff> staff) {
        this.customers = customers;
        this.staff = staff;
    }

    // Default constructor (optional)
    public AuthenticationService() {
        this.customers = DummyData.createDummyCustomers();
        this.staff = DummyData.createDummyStaff();
    }

    public Person authenticate(String username, String password) {
        for (Customer customer : customers) {
            // Debug log to check stored customer data
            System.out.println("Checking customer: " + customer.getEmail() + ", " + customer.getPassword());
    
            if (customer.getEmail() != null && customer.getPassword() != null &&
                customer.getEmail().equals(username) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        for (Staff staffMember : staff) {
            if (staffMember.getEmail() != null && staffMember.getPassword() != null &&
                staffMember.getEmail().equals(username) && staffMember.getPassword().equals(password)) {
                return staffMember;
            }
        }
        return null; // Authentication failed
    }

    public Guest loginAsGuest() {
        String guestId = "guest" + guestCounter++; // Generate a unique guest ID
        return new Guest(guestId);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer); // Ensure customers is properly initialized as a list
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}