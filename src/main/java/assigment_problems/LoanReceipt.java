package assigment_problems;
import java.util.Arrays;
import java.util.regex.Pattern;

class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    private static final Pattern BOOK_ID_PATTERN;

    // Static block for one-time shared regex compilation
    static {
        BOOK_ID_PATTERN = Pattern.compile("^BK-\\d{3}$");
    }

    public LoanReceipt(String memberId, String[] bookIds) {
        if (memberId == null || bookIds == null) {
            throw new IllegalArgumentException("Inputs cannot be null.");
        }
        for (String id : bookIds) {
            if (id == null || !BOOK_ID_PATTERN.matcher(id).matches()) {
                throw new IllegalArgumentException("Invalid book ID format: " + id);
            }
        }
        this.memberId = memberId;
        // Defensive copy on the way in
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getMemberId() {
        return memberId;
    }

    // Defensive copy on the way out
    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    // Wither pattern for immutable record modification
    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        if (newId == null || !BOOK_ID_PATTERN.matcher(newId).matches()) {
            throw new IllegalArgumentException("Invalid book ID format: " + newId);
        }
        String[] updatedIds = Arrays.copyOf(bookIds, bookIds.length);
        updatedIds[index] = newId;
        return new LoanReceipt(this.memberId, updatedIds);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt r : receipts) {
            if (r == null) {
                nullSkipped++;
            } else {
                processed++;
                if (r instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                } else {
                    regular++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " +
                referenceOnly + " reference-only | " + regular + " regular";
    }
}

// Reference-only variant subclass
class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
