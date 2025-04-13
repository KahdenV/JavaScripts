public class Guest extends Person {
    private String id; // Unique ID for the guest

    // Constructor for creating a Guest with a unique ID
    public Guest(String id) {
        super("Guest", "guest", ""); // Calling the parent class constructor with default values
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