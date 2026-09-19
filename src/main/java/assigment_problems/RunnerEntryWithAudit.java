package assigment_problems;
import java.util.Arrays;
public class RunnerEntryWithAudit extends RaceEntryAudit {
    private String category;

    public RunnerEntryWithAudit(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }

    public static void main(String[] args) {
        RunnerEntryWithAudit r = new RunnerEntryWithAudit("BIB2001", 80, "Open 10K");
        r.pay(30);
        r.applyLateFee(20);
        System.out.println(r.getBalanceDue()); // 90.0

        double[] history = r.getLateFeeHistory();
        System.out.println(Arrays.toString(history)); // [40.0]

        // Tampering test
        history[0] = 999;
        System.out.println(Arrays.toString(r.getLateFeeHistory())); // [40.0] (unaffected)
    }
}

class RaceEntryAudit {
    private String bibNumber;
    double entryFee;
    double paidAmount;
    private double[] lateFees = new double[10];
    private int feeCount = 0;

    public RaceEntryAudit(String bibNumber, double entryFee) {
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
    }

    public void pay(double amount) {
        if (amount > 0) this.paidAmount += amount;
    }

    protected void applyLateFee(double amount) {
        if (feeCount < lateFees.length) {
            lateFees[feeCount++] = amount;
        }
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFees, feeCount);
    }

    public double getBalanceDue() {
        double totalLateFees = 0;
        for (int i = 0; i < feeCount; i++) {
            totalLateFees += lateFees[i];
        }
        return Math.max(0.0, (entryFee + totalLateFees) - paidAmount);
    }
}
