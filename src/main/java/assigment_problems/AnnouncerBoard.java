package assigment_problems;
public class AnnouncerBoard {

    public static String announceAll(RaceEntryBase[] entries) {
        StringBuilder sb = new StringBuilder();
        for (RaceEntryBase entry : entries) {
            sb.append(entry.announce());
            if (entry instanceof RelayTeamEntryBase) {
                RelayTeamEntryBase relay = (RelayTeamEntryBase) entry;
                sb.append(" [Team size via downcast: ").append(relay.getTeamSize()).append("] ");
            }
            sb.append("| ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        RunnerEntryBase runner = new RunnerEntryBase("BIB2001", 80, "Open 10K");
        runner.pay(30);
        runner.applyLateFee(20); // balance becomes 90.0

        RelayTeamEntryBase relay = new RelayTeamEntryBase("BIB4001", 300, 4);

        RaceEntryBase[] fleet = { runner, relay };
        System.out.println(announceAll(fleet));
    }
}

class RaceEntryBase {
    private String bibNumber;
    double entryFee;
    double paidAmount;
    private double totalLateFee = 0;

    public RaceEntryBase(String bibNumber, double entryFee) {
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
    }

    public void pay(double amount) {
        if (amount > 0) this.paidAmount += amount;
    }

    public void applyLateFee(double amount) {
        this.totalLateFee += amount;
    }

    public double getBalanceDue() {
        return Math.max(0.0, (entryFee + totalLateFee) - paidAmount);
    }

    public String getBibNumber() {
        return bibNumber;
    }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue();
    }
}

class RunnerEntryBase extends RaceEntryBase {
    private String category;

    public RunnerEntryBase(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    public void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + getBibNumber() + " | Category: " + category + " | Balance: " + getBalanceDue();
    }
}

class RelayTeamEntryBase extends RaceEntryBase {
    private int teamSize;

    public RelayTeamEntryBase(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public String announce() {
        return "Relay Team | Bib: " + getBibNumber() + " | Team Size: " + teamSize + " | Balance: " + getBalanceDue();
    }
}
