import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private List<Order> orderHistory;

    public Customer(String name, String email, String password) {
        super(name, email, password);
        this.orderHistory = new ArrayList<>();
    }
} 