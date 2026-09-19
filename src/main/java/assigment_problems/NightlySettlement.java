package assigment_problems;
public class NightlySettlement {
    private static int bibCounter = 0;

    private final String entryCode;
    private String bibNumber;
    double entryFee;
    double paidAmount;

    public NightlySettlement(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().isEmpty() || bibNumber.length() < 4) {
            throw new IllegalArgumentException("Invalid bib");
        }
        bibCounter++;
        this.entryCode = "CODE-" + bibCounter;
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
    }

    public static int getBibCounter() {
        return bibCounter;
    }

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) return false;
        if (code.charAt(0) != 'M') return false;
        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(code.charAt(i))) return false;
        }
        return Character.isUpperCase(code.charAt(4));
    }

    public void pay(double amount) {
        if (amount > 0) this.paidAmount += amount;
    }

    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    public static String settleNight(NightlySettlement[] entries) {
        int processed = 0;
        int nullSkipped = 0;
        int relayCount = 0;
        int individualCount = 0;

        for (NightlySettlement entry : entries) {
            if (entry == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (entry instanceof RelayTeamNightEntry) {
                relayCount++;
            } else {
                individualCount++;
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + relayCount + " relay | " + individualCount + " individual";
    }

    public static void main(String[] args) {
        System.out.println(isValidDiscountCode("M123A")); // true
        System.out.println(isValidDiscountCode("M12A"));  // false
        System.out.println(isValidDiscountCode("X123A")); // false

        NightlySettlement r = new NightlySettlement("BIB2001", 80);
        r.pay(10, "UPI");

        EliteNightEntry elite = new EliteNightEntry("BIB3001", 150);
        RelayTeamNightEntry relay = new RelayTeamNightEntry("BIB4001", 300);

        NightlySettlement[] batch = { elite, null, relay };
        System.out.println(settleNight(batch));
        System.out.println("Bib Counter Total: " + getBibCounter());
    }
}

class EliteNightEntry extends NightlySettlement {
    public EliteNightEntry(String bibNumber, double entryFee) {
        super(bibNumber, entryFee);
    }
}

class RelayTeamNightEntry extends NightlySettlement {
    public RelayTeamNightEntry(String bibNumber, double entryFee) {
        super(bibNumber, entryFee);
    }
}
