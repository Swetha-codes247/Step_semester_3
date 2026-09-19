package class_problems;
class EventTicket {
    private static int ticketsIssued = 0;

    protected final String ticketId;
    protected double basePrice;
    protected double amountPaid;

    public EventTicket(double basePrice) {
        ticketsIssued++;
        ticketId = "TCK-" + (1000 + ticketsIssued);
        this.basePrice = basePrice;
    }

    public void pay(double amount) {
        amountPaid += amount;
    }

    public void pay(double amount, String mode) {
        System.out.println("Payment Mode: " + mode);
        pay(amount);
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }

    public static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5)
            return false;

        return code.charAt(0) == 'F'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isDigit(code.charAt(3))
                && Character.isUpperCase(code.charAt(4));
    }

    public static int getTicketsIssued() {
        return ticketsIssued;
    }
}

class GroupTicket extends EventTicket {
    private int groupSize;

    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);
        this.groupSize = groupSize;
    }
}

public class EventTicketTest5 {

    public static String processNightlySettlement(EventTicket[] tickets) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (EventTicket ticket : tickets) {
            if (ticket == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (ticket instanceof GroupTicket)
                group++;
            else
                individual++;
        }

        return processed + " processed | " +
                nullSkipped + " null skipped | " +
                group + " group | " +
                individual + " individual";
    }

    public static void main(String[] args) {
        EventTicket t1 = new EventTicket(500);

        System.out.println(t1.ticketId);
        System.out.println(EventTicket.getTicketsIssued());

        System.out.println(EventTicket.isValidPromoCode("F123A"));
        System.out.println(EventTicket.isValidPromoCode("F12A"));
        System.out.println(EventTicket.isValidPromoCode("X123A"));

        t1.pay(200);
        t1.pay(200, "UPI");

        System.out.println(t1.getBalanceDue());

        EventTicket[] tickets = {
                new GroupTicket(2000, 5),
                null,
                new EventTicket(500)
        };

        System.out.println(processNightlySettlement(tickets));
    }
}
