public class MenuManager {
    private CustomerMenu customerMenu;
    private StaffMenu staffMenu;

    public MenuManager(CustomerMenu customerMenu, StaffMenu staffMenu) {
        this.customerMenu = customerMenu;
        this.staffMenu = staffMenu;
    }

    /**
     * Dynamically sets the CustomerMenu instance.
     *
     * @param customerMenu The CustomerMenu to set.
     */
    public void setCustomerMenu(CustomerMenu customerMenu) {
        this.customerMenu = customerMenu;
    }

    /**
     * Displays the main menu based on the type of user (customer, guest, or staff).
     *
     * @param user The authenticated user.
     */
    public void displayMainMenu(Person user) {
        if (user instanceof Staff) {
            staffMenu.showStaffMenu();
        } else if (user instanceof Customer || user instanceof Guest) {
            customerMenu.showCustomerMenu();
        } else {
            System.out.println("Unknown user type. Unable to display menu.");
        }
    }
}