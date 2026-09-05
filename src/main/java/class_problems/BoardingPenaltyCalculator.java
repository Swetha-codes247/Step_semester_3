package class_problems;
public final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        if (minimumPenaltyPercent < 0) {
            throw new IllegalArgumentException("Minimum penalty percent cannot be negative");
        }
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0) {
            throw new IllegalArgumentException("ticketFare cannot be negative");
        }
        if (minutesLate < 0) {
            throw new IllegalArgumentException("minutesLate cannot be negative");
        }

        // Passengers on time incur no penalty and never trigger the floor
        if (minutesLate == 0) {
            return 0.0;
        }

        // Tier 1: minutes 1 to 5 @ 0.5%
        int tier1Minutes = Math.min(minutesLate, 5);
        // Tier 2: minutes 6 to 15 @ 1.0%
        int tier2Minutes = Math.max(0, Math.min(minutesLate - 5, 10));
        // Tier 3: minutes 16+ @ 2.0%
        int tier3Minutes = Math.max(0, minutesLate - 15);

        double tieredPenalty = ticketFare * (
                (tier1Minutes * 0.005) +
                        (tier2Minutes * 0.010) +
                        (tier3Minutes * 0.020)
        );

        // Minimum floor fee applies only if late
        double minFloor = ticketFare * (minimumPenaltyPercent / 100.0);

        return Math.max(tieredPenalty, minFloor);
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator calc = new BoardingPenaltyCalculator(1.0); // 1% floor

        System.out.println("Rs " + calc.calculatePenalty(1000, 0));  // Rs 0.0
        System.out.println("Rs " + calc.calculatePenalty(1000, 1));  // Rs 10.0
        System.out.println("Rs " + calc.calculatePenalty(1000, 16)); // Rs 145.0
    }
}
