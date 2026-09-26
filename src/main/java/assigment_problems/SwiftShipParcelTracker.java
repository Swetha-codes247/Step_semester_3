package assigment_problems;
import java.util.*;

interface ShippingRule {
    double calculateCharge(double weight);
}

class StandardShipping implements ShippingRule {
    public double calculateCharge(double weight) { return 40 + (10 * weight); }
}

class ExpressShipping implements ShippingRule {
    public double calculateCharge(double weight) { return 80 + (15 * weight); }
}

class FragileShipping implements ShippingRule {
    private final StandardShipping standard = new StandardShipping();
    public double calculateCharge(double weight) { return standard.calculateCharge(weight) + 50; }
}

enum Status { BOOKED, PICKED_UP, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED }

class Parcel {
    String id;
    double weight;
    ShippingRule rule;
    Status status = Status.BOOKED;
    List channels;

    public Parcel(String id, double weight, ShippingRule rule, List channels) {
        this.id = id;
        this.weight = weight;
        this.rule = rule;
        this.channels = channels;
        notifyChannels();
    }

    public void updateStatus(Status newStatus) {
        boolean valid = (status == Status.BOOKED && newStatus == Status.PICKED_UP) ||
                (status == Status.PICKED_UP && newStatus == Status.IN_TRANSIT) ||
                (status == Status.IN_TRANSIT && newStatus == Status.OUT_FOR_DELIVERY) ||
                (status == Status.OUT_FOR_DELIVERY && newStatus == Status.DELIVERED);
        if (valid) {
            this.status = newStatus;
            notifyChannels();
        } else {
            System.out.println("Invalid transition: " + status + " → " + newStatus + " is not allowed.");
        }
    }

    public void cancel() {
        if (status == Status.BOOKED) {
            System.out.println("Parcel " + id + " cancelled successfully.");
        } else {
            System.out.println("Cancellation failed: " + id + " can be cancelled only while BOOKED.");
        }
    }

    private void notifyChannels() {
        for (String ch : channels) {
            System.out.println("[" + ch + "] " + id + " is now " + status + ".");
        }
    }
}

public class SwiftShipParcelTracker {
    public static void main(String[] args) {
        List subs = Arrays.asList("SMS", "Email");
        Parcel p101 = new Parcel("P101", 2.0, new ExpressShipping(), subs);
        System.out.printf("Parcel P101 booked (Express, 2 kg). Charge: ₹%.2f.\n", p101.rule.calculateCharge(p101.weight));

        p101.updateStatus(Status.PICKED_UP);
        p101.cancel();
        p101.updateStatus(Status.IN_TRANSIT);
        p101.updateStatus(Status.DELIVERED);
    }
}
