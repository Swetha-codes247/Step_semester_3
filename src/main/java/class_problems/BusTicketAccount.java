package class_problems;
public class BusTicketAccount {
    private final String bookingId;
    private final double ticketFare;

    private static double standardLateFeePerMinute;

    static {
        // One-time static setup for default rates
        standardLateFeePerMinute = 15.0;
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        if (bookingId == null || bookingId.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid booking ID");
        }
        this.bookingId = bookingId;
        this.ticketFare = Math.max(0.0, ticketFare);
    }

    // Provisional constructor chaining to the full constructor
    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getTicketFare() {
        return ticketFare;
    }

    // Final penalty calculation (flat rate based on ticketFare & minutesLate)
    public final double calculatePenalty(int minutesLate) {
        if (minutesLate <= 0) return 0.0;
        return minutesLate * (ticketFare * 0.01);
    }

    // Subclass for sleeper coach accounts
    public static class SleeperAccount extends BusTicketAccount {
        public SleeperAccount(String bookingId, double ticketFare) {
            super(bookingId, ticketFare);
        }
    }

    public void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        // Processing logic per individual account
    }

    public static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        // A depot manager cannot reconcile corrupt records where arrays do not match
        if (accounts == null || amounts == null || minutesLateArray == null ||
                accounts.length != amounts.length || accounts.length != minutesLateArray.length) {
            throw new IllegalArgumentException("Mismatched parallel input batch arrays");
        }

        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;
        double grandTotalPenalties = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            BusTicketAccount account = accounts[i];
            if (account == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            int minutesLate = Math.max(0, minutesLateArray[i]);
            double penalty = account.calculatePenalty(minutesLate);

            if (account instanceof SleeperAccount) {
                sleeperCount++;
                // Sleeper coaches settle with a flat 20% surcharge on late penalties
                penalty += penalty * 0.20;
            } else {
                regularCount++;
            }

            grandTotalPenalties += penalty;
        }

        System.out.printf("%d processed | %d null skipped | %d sleeper | %d regular | grand total penalties = %.2f%n",
                processed, nullSkipped, sleeperCount, regularCount, grandTotalPenalties);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
                new SleeperAccount("BK001", 2000),
                null,
                new BusTicketAccount("BK002", 1200)
        };
        double[] amounts = {1200, 900, 700};
        int[] minutesLateArray = {10, 5, 0};

        processBatch(accounts, amounts, minutesLateArray);
    }
}
