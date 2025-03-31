public class Staff extends Person {
    private StaffRole role;

    public Staff(String name, String email, String password, StaffRole role) {
        super(name, email, password);
        this.role = role;
    }
} 