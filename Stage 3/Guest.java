public class Guest extends Person {
    private String id; // Unique ID for the guest

    public Guest(String id) {
        super("Guest", "guest", ""); // Default values for Guest
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Guest ID: " + id;
    }
}