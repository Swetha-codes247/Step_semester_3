package class_problems;
import java.util.regex.Pattern;

// Base Immutable Discharge Summary
class DischargeSummary {
    private static final Pattern MED_CODE_PATTERN;

    static {
        // One-time shared regex initialization
        MED_CODE_PATTERN = Pattern.compile("^MED-[A-Z]$");
    }

    private final String patientId;
    private final String[] medicationCodes;

    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (patientId == null || patientId.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient ID cannot be null or empty");
        }
        if (medicationCodes == null) {
            throw new IllegalArgumentException("Medication codes cannot be null");
        }

        // Validate all medication codes
        for (String code : medicationCodes) {
            if (code == null || !MED_CODE_PATTERN.matcher(code).matches()) {
                throw new IllegalArgumentException("Invalid medication code format: " + code);
            }
        }

        this.patientId = patientId;
        // Defensive copy on entry
        this.medicationCodes = medicationCodes.clone();
    }

    public String getPatientId() {
        return this.patientId;
    }

    /**
     * Defensive copy on access.
     */
    public String[] getMedicationCodes() {
        return this.medicationCodes.clone();
    }

    /**
     * Wither method: returns a fresh instance with corrected medication.
     */
    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        if (index < 0 || index >= this.medicationCodes.length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (newCode == null || !MED_CODE_PATTERN.matcher(newCode).matches()) {
            throw new IllegalArgumentException("Invalid replacement code format: " + newCode);
        }

        String[] updatedCodes = this.medicationCodes.clone();
        updatedCodes[index] = newCode;
        return new DischargeSummary(this.patientId, updatedCodes);
    }

    /**
     * Reconciles a batch of discharge summaries without throwing on null elements.
     */
    public static String processNightlyBatch(DischargeSummary[] summaries) {
        if (summaries == null) {
            return "0 processed | 0 null skipped | 0 critical-care | 0 routine";
        }

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                nullSkipped++;
            } else {
                processed++;
                if (summary instanceof CriticalCareDischargeSummary) {
                    criticalCare++;
                } else {
                    routine++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + criticalCare + " critical-care | " + routine + " routine";
    }
}

// Subclass for critical care discharges
class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;

    public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
        super(patientId, medicationCodes);
        if (icuDays < 0) {
            throw new IllegalArgumentException("ICU days cannot be negative.");
        }
        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return this.icuDays;
    }

    @Override
    public CriticalCareDischargeSummary withCorrectedMedication(int index, String newCode) {
        String[] updatedCodes = getMedicationCodes();
        if (index < 0 || index >= updatedCodes.length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (newCode == null || !newCode.matches("^MED-[A-Z]$")) {
            throw new IllegalArgumentException("Invalid replacement code format: " + newCode);
        }
        updatedCodes[index] = newCode;
        return new CriticalCareDischargeSummary(getPatientId(), updatedCodes, this.icuDays);
    }
}
