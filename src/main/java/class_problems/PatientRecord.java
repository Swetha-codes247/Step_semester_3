package class_problems;
import java.util.*;

class AccessRuleEngine {

    /**
     * Determines whether field access is ALLOWED or DENIED based on standard Java visibility rules.
     */
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier) {
            case "public":
                return "ALLOWED";

            case "protected":
            case "default":
                // Without inheritance, protected behaves identically to package-private (default)
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "private":
                if ("SAME_CLASS".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            default:
                return "DENIED";
        }
    }

    /**
     * Summarizes access attempts in the format: "Allowed: X | Denied: Y"
     */
    public static String summarizeBatch(String[][] attempts) {
        if (attempts == null) {
            return "Allowed: 0 | Denied: 0";
        }

        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {
            if (attempt != null && attempt.length >= 2) {
                String result = classifyAccess(attempt[0], attempt[1]);
                if ("ALLOWED".equals(result)) {
                    allowed++;
                } else {
                    denied++;
                }
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }
}

class PatientRecord {
    // Encapsulated fields choosing appropriate access modifiers:
    // Core patient identifier is strictly private
    private final String patientId;
    // Internal clinical routing codes can be accessed within the clinic package
    String wardCode;
    // Vitals score can be accessible to subclasses (e.g., ICU ward)
    protected double vitalsScore;
    // Facility name is general public metadata
    public String facilityName;

    // No usable no-arg constructor provided

    public PatientRecord(String patientId, String wardCode, double vitalsScore, String facilityName) {
        // String validation gate: must not be blank, whitespace-only, or < 4 characters
        if (patientId == null || patientId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid patientId: must be non-blank and at least 4 characters long.");
        }
        this.patientId = patientId.trim();
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }

    public String getPatientId() {
        return patientId;
    }
}
