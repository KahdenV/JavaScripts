import java.util.HashMap;
import java.util.Map;

public class Concession {
    private final String concessionId;
    private String itemName;
    private double price;

    // Concession menu to store items (itemId -> Concession object)
    protected static final Map<String, Concession> concessionMenu = new HashMap<>();


    public static Map<String, Concession> getConcessionMenu() {
        return concessionMenu;
    }

    // Constructor
    public Concession(String concessionId, String itemName, double price) {
        this.concessionId = concessionId;
        this.itemName = itemName;
        this.price = price;
    }

    // Getters
    public String getConcessionId() { return concessionId; }
    public String getItemName() { return itemName; }
    public double getPrice() { return price; }

    // Process the sale of a concession item
    public static void processSale(String itemId, String customerId) {
        if (checkTicketPurchase(customerId)) {
            Concession item = concessionMenu.get(itemId);
            if (item != null) {
                System.out.println("Concession item sold: " + item.itemName + " for $" + item.price);
            } else {
                System.out.println("Item not found in the concession menu.");
            }
        } else {
            System.out.println("Customer must purchase a ticket before buying concessions.");
        }
    }

    // Check if the customer has purchased a ticket
    public static boolean checkTicketPurchase(String customerId) {
        // Here you would integrate with the ticketing system to verify purchase
        // For now, simulate that the customer has a ticket
        return true; // Assume the customer has bought a ticket
    }

    // Add a new item to the concession menu
    public static void addItem(String itemId, String itemName, double price) {
        Concession item = new Concession(itemId, itemName, price);
        concessionMenu.put(itemId, item);
        System.out.println("Item added: " + itemName + " ($" + price + ")");
    }

    // Remove an item from the concession menu
    public static void removeItem(String itemId) {
        if (concessionMenu.remove(itemId) != null) {
            System.out.println("Item removed: " + itemId);
        } else {
            System.out.println("Item not found: " + itemId);
        }
    }

    // Update an existing item in the concession menu
    public static void updateItem(String itemId, String itemName, double price) {
        Concession item = concessionMenu.get(itemId);
        if (item != null) {
            item.itemName = itemName;
            item.price = price;
            System.out.println("Item updated: " + itemName + " ($" + price + ")");
        } else {
            System.out.println("Item not found: " + itemId);
        }
    }

    // Search for an item in the concession menu
    public static void searchItem(String itemId) {
        Concession item = concessionMenu.get(itemId);
        if (item != null) {
            System.out.println("Item found: " + item.itemName + " ($" + item.price + ")");
        } else {
            System.out.println("Item not found: " + itemId);
        }
    }
}
