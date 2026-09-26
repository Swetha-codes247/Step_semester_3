package assigment_problems;
import java.util.*;

interface PricingPlan { double getPrice(double basePrice); }
class HostellerPlan implements PricingPlan { public double getPrice(double base) { return base * 0.9; } }

class Transaction {
    String desc; double amount; boolean isRefund;
    public Transaction(String desc, double amount, boolean isRefund) {
        this.desc = desc; this.amount = amount; this.isRefund = isRefund;
    }
}

class SmartCard {
    String id; PricingPlan plan; double balance = 0;
    List history = new ArrayList<>();
    Map purchases = new HashMap<>();

    public SmartCard(String id, PricingPlan plan) { this.id = id; this.plan = plan; }

    public void topUp(double amt) {
        balance += amt;
        history.add(new Transaction("Top-up", amt, false));
        System.out.printf("C-2045 topped up with %.2f. Balance: ₹%.2f.\n", amt, balance);
    }

    public void purchase(String item, double basePrice) {
        double finalPrice = plan.getPrice(basePrice);
        if (balance >= finalPrice) {
            balance -= finalPrice;
            purchases.put(item, finalPrice);
            history.add(new Transaction(item, finalPrice, true));
            System.out.printf("%s purchased for %.2f. Balance: ₹%.2f.\n", item, finalPrice, balance);
        } else {
            System.out.printf("Purchase failed: Insufficient balance (required %.2f, available %.2f).\n", finalPrice, balance);
        }
    }

    public void refund(String item) {
        if (purchases.containsKey(item)) {
            double amt = purchases.remove(item);
            balance += amt;
            history.add(new Transaction("Refund: " + item, amt, false));
            System.out.printf("Refund of ₹%.2f for %s processed. Balance: ₹%.2f.\n", amt, item, balance);
        } else {
            System.out.println("Refund rejected: " + item + " has already been refunded.");
        }
    }

    public void miniStatement() {
        System.out.print("Mini-statement for " + id + ": ");
        StringBuilder sb = new StringBuilder();
        for (Transaction t : history) {
            sb.append(t.isRefund ? "-" : "+").append(String.format("%.2f, ", t.amount));
        }
        System.out.println(sb.toString() + "= " + balance);
    }
}

public class CampusCanteenSmartCard {
    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new HostellerPlan());
        card.topUp(500.0);
        card.purchase("Veg Thali", 120.0);
        card.purchase("Cold Coffee", 60.0);
        card.purchase("Items", 400.0);
        card.refund("Veg Thali");
        card.refund("Veg Thali");
        card.miniStatement();
    }
}
