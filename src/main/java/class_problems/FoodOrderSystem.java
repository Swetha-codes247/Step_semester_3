package class_problems;
import java.util.ArrayList;
import java.util.List;

// Payment Method Interface (Dependency on Abstractions)
interface IPaymentMethod {
    boolean processPayment(double amount);
    String getMethodName();
}

// Concrete Payment Methods
class CreditCardPayment implements IPaymentMethod {
    public boolean processPayment(double amount) {
        // Simulate successful payment
        return true;
    }
    public String getMethodName() { return "Credit Card"; }
}

class DigitalWalletPayment implements IPaymentMethod {
    public boolean processPayment(double amount) {
        // Simulate failed payment for demonstration
        return false;
    }
    public String getMethodName() { return "Digital Wallet"; }
}

// Food Item and Line Item (Composition over Inheritance)
class FoodItem {
    String name;
    double price;
    public FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class LineItem {
    FoodItem foodItem;
    int quantity;
    public LineItem(FoodItem foodItem, int quantity) {
        this.foodItem = foodItem;
        this.quantity = quantity;
    }
}

// Order Class
class Order {
    int orderId;
    List lineItems = new ArrayList<>();
    String status = "Created";

    public Order(int orderId) {
        this.orderId = orderId;
    }

    public void addItem(FoodItem item, int qty) {
        lineItems.add(new LineItem(item, qty));
    }

    public double calculateTotal() {
        double total = 0;
        for (LineItem li : lineItems) {
            total += li.foodItem.price * li.quantity;
        }
        return total;
    }

    public void placeOrder(IPaymentMethod paymentMethod) {
        if (lineItems.isEmpty()) {
            System.out.println("Cannot place order: Order must contain at least one item.");
            return;
        }

        System.out.println("Order placed.");
        boolean paymentSuccess = paymentMethod.processPayment(calculateTotal());

        if (paymentSuccess) {
            this.status = "Paid";
            System.out.println("Payment via " + paymentMethod.getMethodName() + " successful. Order status: Paid.");
            System.out.println("Notification: Order #" + orderId + " placed and paid.");
        } else {
            this.status = "Pending Payment";
            System.out.println("Payment via " + paymentMethod.getMethodName() + " failed. Order status: Pending Payment.");
            System.out.println("Notification: Order #" + orderId + " placed, awaiting payment.");
        }
    }
}

// Main Execution Class matching Sample Input & Expected Output
public class FoodOrderSystem {
    public static void main(String[] args) {
        FoodItem pizza = new FoodItem("Pizza", 250.0);
        FoodItem soda = new FoodItem("Soda", 50.0);
        FoodItem burger = new FoodItem("Burger", 150.0);

        // 1. Customer creates an order, adds 'Pizza (Qty 2)' and 'Soda (Qty 1)'[cite: 2]
        Order order123 = new Order(123);
        System.out.println("Order created. Added Pizza (Qty 2), Soda (Qty 1).");
        order123.addItem(pizza, 2);
        order123.addItem(soda, 1);

        // 2. Customer attempts to place the order with an empty cart (should fail)[cite: 2]
        Order emptyOrder = new Order(999);
        emptyOrder.placeOrder(new CreditCardPayment());

        // 3. Customer places the order, attempts payment via Credit Card (simulate success)[cite: 2]
        order123.placeOrder(new CreditCardPayment());

        // 4. Customer creates another order, adds 'Burger (Qty 1)', attempts payment via Digital Wallet (simulate failure)[cite: 2]
        Order order124 = new Order(124);
        System.out.println("Order created. Added Burger (Qty 1).");
        order124.addItem(burger, 1);
        order124.placeOrder(new DigitalWalletPayment());
    }
}