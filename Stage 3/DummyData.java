import java.util.List;
import java.util.ArrayList;

/**
 * Utility class for creating dummy data for customers and staff.
 */
public class DummyData {
    /**
     * Creates a list of dummy customers.
     *
     * @return A list of dummy customers.
     */
    public static List<Customer> createDummyCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("John Doe", "john", "pass123"));
        customers.add(new Customer("Jane Smith", "jane", "pass456"));
        return customers;
    }

    /**
     * Creates a list of dummy staff members.
     *
     * @return A list of dummy staff members.
     */
    public static List<Staff> createDummyStaff() {
        List<Staff> staff = new ArrayList<>();
        staff.add(new Staff("Admin User", "admin", "admin123"));
        staff.add(new Staff("Theater Staff", "staff", "staff123"));
        return staff;
    }
}