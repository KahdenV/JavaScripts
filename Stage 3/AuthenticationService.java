import java.util.List;

public class AuthenticationService {
    private List<Customer> customers;
    private List<Staff> staff;
    private int guestCounter = 1; // Track guest IDs

    // Constructor
    public AuthenticationService(List<Customer> customers, List<Staff> staff) {
        this.customers = customers;
        this.staff = staff;
        // Add dummy data (if applicable)
        this.customers.addAll(DummyData.createDummyCustomers());
        this.staff.addAll(DummyData.createDummyStaff());
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer); // Add new customer to the list
    }

    public Guest loginAsGuest() {
        String guestId = "guest" + guestCounter++;
        return new Guest(guestId); 
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
            System.out.println("Checking staff: " + staffMember.getEmail() + ", " + staffMember.getPassword());

            if (staffMember.getEmail() != null && staffMember.getPassword() != null &&
                staffMember.getEmail().equals(username) && staffMember.getPassword().equals(password)) {
                return staffMember;
            }
        }
        return null; // Authentication failed
    }
}