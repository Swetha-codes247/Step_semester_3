package class_problems;
class EventTicket {
    protected double basePrice;
    protected double amountPaid;

    private double[] lateFeeHistory = new double[10];
    private int feeCount = 0;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
    }

    public void pay(double amount) {
        amountPaid += amount;
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }

    protected void applyLateFee(double amount) {
        basePrice += amount;
        lateFeeHistory[feeCount++] = amount;
    }

    public double[] getLateFeeHistory() {
        double[] copy = new double[feeCount];

        for (int i = 0; i < feeCount; i++)
            copy[i] = lateFeeHistory[i];

        return copy;
    }
}

public class WorkshopTicket extends EventTicket {

    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }

    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket(1200);

        w.pay(1200);
        w.applyLateFee(100);

        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();

        history[0] = 999;

        System.out.println(w.getLateFeeHistory()[0]);
    }
}
