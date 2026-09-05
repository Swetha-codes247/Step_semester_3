package assigment_problems;
public class FoodOrder {
    private final String studentName;
    private final String dishName;
    private boolean delivered;

    // Parameterized constructor enforcing non-blank validation
    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid student name: Name cannot be null or whitespace.");
        }
        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid dish name: Dish cannot be null or whitespace.");
        }
        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
        this.delivered = false;
    }

    public void markDelivered() {
        if (this.delivered) {
            System.out.println("Warning: Order for " + studentName + " (" + dishName + ") has ALREADY been delivered!");
        } else {
            this.delivered = true;
            System.out.println("Order for " + studentName + " (" + dishName + ") successfully marked as delivered.");
        }
    }

    public static void processBatch(String[][] rawOrders) {
        if (rawOrders == null) {
            System.out.println("Valid: 0 | Rejected: 0");
            return;
        }

        int valid = 0;
        int rejected = 0;

        for (String[] entry : rawOrders) {
            if (entry == null || entry.length < 2) {
                rejected++;
                continue;
            }
            try {
                new FoodOrder(entry[0], entry[1]);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] rawOrders = {
                {"Ravi", "Paneer Butter Masala"},
                {"", "Chole Bhature"},
                {"Meera", "   "},
                {"Divya", "Veg Biryani"}
        };
        FoodOrder.processBatch(rawOrders);
    }
}
