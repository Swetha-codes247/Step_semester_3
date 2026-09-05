package assigment_problems;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DeliverySlot {
    private static final String DEFAULT_SLOT = "ASAP";
    private static final Set<String> PEAK_HOURS = new HashSet<>(Arrays.asList(
            "12:00-13:00",
            "13:00-14:00",
            "19:00-20:00",
            "20:00-21:00"
    ));

    private final String orderId;
    private final String timeSlot;

    // Chained constructor assigning default slot
    public DeliverySlot(String orderId) {
        this(orderId, DEFAULT_SLOT);
    }

    // Full constructor
    public DeliverySlot(String orderId, String timeSlot) {
        this.orderId = orderId;
        this.timeSlot = (timeSlot == null || timeSlot.trim().isEmpty()) ? DEFAULT_SLOT : timeSlot;
    }

    public boolean isPeakHour() {
        return PEAK_HOURS.contains(this.timeSlot);
    }

    public static void main(String[] args) {
        System.out.println(new DeliverySlot("ORD101", "13:00-14:00").isPeakHour()); // true
        System.out.println(new DeliverySlot("ORD102").isPeakHour());                // false
    }
}
