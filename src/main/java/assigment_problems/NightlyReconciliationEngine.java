package assigment_problems;
class DeliveryAccount {
    private static final double DEFAULT_INITIAL_VALUE;
    protected final String studentId;
    protected double orderValue;

    // Static initialization block for class-level configuration
    static {
        DEFAULT_INITIAL_VALUE = 0.0;
    }

    // Chained constructor
    public DeliveryAccount(String studentId) {
        this(studentId, DEFAULT_INITIAL_VALUE);
    }

    // Full constructor
    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public double getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(double orderValue) {
        this.orderValue = orderValue;
    }

    // Final surge calculation method (standard regular tiered account)
    public final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes <= 0 || orderValue <= 0) {
            return 0.0;
        }
        int t1 = Math.min(delayMinutes, 5);
        int t2 = Math.max(0, Math.min(delayMinutes - 5, 10));
        int t3 = Math.max(0, delayMinutes - 15);

        return (t1 * 0.005 * orderValue) + (t2 * 0.010 * orderValue) + (t3 * 0.020 * orderValue);
    }
}

class PremiumDeliveryAccount extends DeliveryAccount {
    public PremiumDeliveryAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public PremiumDeliveryAccount(String studentId) {
        super(studentId);
    }

    // Flat 50% waiver applied to surge fees for premium users
    public double calculatePremiumSurgeFee(int delayMinutes) {
        return calculateSurgeFee(delayMinutes) * 0.50;
    }
}

public class NightlyReconciliationEngine {

    public static void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        account.setOrderValue(amount);
    }

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        // Operational Safety Check: Reject mismatched array lengths immediately
        // Justification: Processing mismatched arrays runs a silent desynchronization risk,
        // charging wrong amounts to wrong student accounts.
        if (accounts == null || amounts == null || delayMinutesArray == null) {
            throw new IllegalArgumentException("Batch input arrays cannot be null.");
        }
        if (accounts.length != amounts.length || accounts.length != delayMinutesArray.length) {
            throw new IllegalArgumentException(
                    "Batch alignment error: Array dimensions must match (accounts="
                            + accounts.length + ", amounts=" + amounts.length
                            + ", delays=" + delayMinutesArray.length + ")"
            );
        }

        int processedCount = 0;
        int nullCount = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double grandTotalSurge = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            DeliveryAccount account = accounts[i];
            if (account == null) {
                nullCount++;
                continue;
            }

            double amount = amounts[i];
            int delay = delayMinutesArray[i];

            processAccount(account, amount, delay);

            double fee;
            if (account instanceof PremiumDeliveryAccount) {
                premiumCount++;
                fee = ((PremiumDeliveryAccount) account).calculatePremiumSurgeFee(delay);
            } else {
                regularCount++;
                fee = account.calculateSurgeFee(delay);
            }

            grandTotalSurge += fee;
            processedCount++;
        }

        System.out.println(
                processedCount + " processed | "
                        + nullCount + " null skipped | "
                        + premiumCount + " premium | "
                        + regularCount + " regular | grand total surge fees = "
                        + grandTotalSurge
        );
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
                new PremiumDeliveryAccount("STU001", 500),
                null,
                new DeliveryAccount("STU002", 300)
        };
        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};

        processBatch(accounts, amounts, delayMinutesArray);
    }
}
