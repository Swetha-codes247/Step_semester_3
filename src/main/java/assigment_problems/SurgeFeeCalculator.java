package assigment_problems;
public final class SurgeFeeCalculator {
    private final double minimumSurgePercent; // Surge floor percentage, e.g. 1.0 for 1%

    public SurgeFeeCalculator(double minimumSurgePercent) {
        if (minimumSurgePercent < 0) {
            throw new IllegalArgumentException("Minimum surge percentage cannot be negative.");
        }
        this.minimumSurgePercent = minimumSurgePercent;
    }

    // Closed-form O(1) tiered fee calculation
    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0) {
            throw new IllegalArgumentException("Order value cannot be negative: " + orderValue);
        }
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay minutes cannot be negative: " + delayMinutes);
        }

        // Zero delay means order arrived on time; minimum surge floor does NOT apply
        if (delayMinutes == 0 || orderValue == 0) {
            return 0.0;
        }

        // Bracket 1: Minutes 1 to 5 @ 0.5% per min
        int tier1Minutes = Math.min(delayMinutes, 5);
        // Bracket 2: Minutes 6 to 15 @ 1.0% per min
        int tier2Minutes = Math.max(0, Math.min(delayMinutes - 5, 10));
        // Bracket 3: Minute 16 onwards @ 2.0% per min
        int tier3Minutes = Math.max(0, delayMinutes - 15);

        double tieredSurge = (tier1Minutes * 0.005 * orderValue)
                + (tier2Minutes * 0.010 * orderValue)
                + (tier3Minutes * 0.020 * orderValue);

        double floorSurge = (minimumSurgePercent / 100.0) * orderValue;

        return Math.max(tieredSurge, floorSurge);
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calc = new SurgeFeeCalculator(1.0); // 1% minimum floor

        System.out.println("Rs " + calc.calculateSurgeFee(500, 0));  // Rs 0.0
        System.out.println("Rs " + calc.calculateSurgeFee(500, 1));  // Rs 5.0 (floor applied)
        System.out.println("Rs " + calc.calculateSurgeFee(500, 16)); // Rs 72.5
    }
}
