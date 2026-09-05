package assigment_problems;
public class Canteen implements Comparable<Canteen> {
    private final String canteenCode;
    private final String canteenName;
    private final int trustScore;

    // Chained constructor with default trustScore of 3
    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    // Full constructor resolving naming conflicts with this
    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public String getCanteenCode() {
        return canteenCode;
    }

    @Override
    public int compareTo(Canteen other) {
        // 1. Higher trust score ranks first (descending order)
        if (this.trustScore != other.trustScore) {
            return Integer.compare(other.trustScore, this.trustScore);
        }

        // 2. Tie-break: Case-insensitive alphanumeric order on canteenCode
        int codeComparison = this.canteenCode.compareToIgnoreCase(other.canteenCode);
        if (codeComparison != 0) {
            return codeComparison;
        }

        // 3. Fallback tie-break: Shorter canteenName length first
        return Integer.compare(this.canteenName.length(), other.canteenName.length());
    }

    // In-place manual insertion sort (no built-in sorting libraries)
    public static Canteen[] rankCanteens(Canteen[] canteens) {
        if (canteens == null || canteens.length <= 1) {
            return canteens;
        }

        Canteen[] sorted = canteens.clone();
        for (int i = 1; i < sorted.length; i++) {
            Canteen key = sorted[i];
            int j = i - 1;
            while (j >= 0 && sorted[j].compareTo(key) > 0) {
                sorted[j + 1] = sorted[j];
                j--;
            }
            sorted[j + 1] = key;
        }
        return sorted;
    }

    public static void main(String[] args) {
        Canteen[] list = {
                new Canteen("HB3-C", "Spice Junction", 3),
                new Canteen("hb1-c", "Grand Mess", 5),
                new Canteen("HB2-C", "Southern Treats") // trustScore defaults to 3
        };

        Canteen[] ranked = rankCanteens(list);
        for (Canteen c : ranked) {
            System.out.print(c.getCanteenCode() + " ");
        }
        // Output: hb1-c HB2-C HB3-C
    }
}
