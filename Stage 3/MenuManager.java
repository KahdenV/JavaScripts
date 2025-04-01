/**
 * Manages the display of menus for customers and staff.
 */
public class MenuManager {
    private CustomerMenu customerMenu;
    private StaffMenu staffMenu;

    public MenuManager(CustomerMenu customerMenu, StaffMenu staffMenu) {
        this.customerMenu = customerMenu;
        this.staffMenu = staffMenu;
    }

    /**
     * Displays the main menu based on the type of user (customer or staff).
     *
     * @param user The authenticated user.
     */
    public void displayMainMenu(Person user) {
        if (user instanceof Staff) {
            staffMenu.showStaffMenu();
        } else if (user instanceof Customer) {
            customerMenu.showCustomerMenu();
        }
    }
}