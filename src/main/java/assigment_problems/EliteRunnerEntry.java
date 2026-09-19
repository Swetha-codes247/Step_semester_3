package assigment_problems;
public class EliteRunnerEntry extends RunnerEntry {
    private double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee, String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    @Override
    public String announce() {
        return "Elite Runner Bib: " + getBibNumber() + " | Category: " + getCategory() +
                " | Sponsor Bonus: " + sponsorBonus + " | Balance: " + getBalanceDue();
    }

    public static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        } else if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        }
        return "Base or direct runner entry";
    }

    public static double getTotalBalanceDue(RaceEntry[] entries) {
        double total = 0;
        for (RaceEntry entry : entries) {
            total += entry.getBalanceDue();
        }
        return total;
    }

    public static void main(String[] args) {
        RunnerEntry runner = new RunnerEntry("BIB2001", 80, "Open 10K");
        EliteRunnerEntry elite = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relay = new RelayTeamEntry("BIB4001", 300, 4);

        System.out.println(runner.announce());
        System.out.println(elite.announce());
        System.out.println(relay.announce());

        System.out.println(classifyGeneration(elite));
        System.out.println(classifyGeneration(relay));

        RaceEntry[] mixedField = { runner, elite, relay };
        System.out.println(getTotalBalanceDue(mixedField)); // 530.0
    }
}

class RaceEntry {
    private String bibNumber;
    double entryFee;
    double paidAmount;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().isEmpty() || bibNumber.length() < 4) {
            throw new IllegalArgumentException("Invalid bib");
        }
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.paidAmount = 0.0;
    }

    public void pay(double amount) {
        if (amount > 0) this.paidAmount += amount;
    }

    public double getBalanceDue() {
        return Math.max(0.0, this.entryFee - this.paidAmount);
    }

    public String getBibNumber() {
        return bibNumber;
    }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue();
    }
}

class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + getBibNumber() + " | Category: " + category + " | Balance: " + getBalanceDue();
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
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