package class_problems;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class PatientProfile {
    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPinHash; // One-way write-only storage
    private boolean idSet = false; // Write-once guard

    // 1. JavaBean-required no-argument constructor
    public PatientProfile() {
        this(null, null);
    }

    // 2. Partial data constructor (name-only)
    public PatientProfile(String name) {
        this(null, name);
    }

    // 3. Primary master constructor
    public PatientProfile(String patientId, String name) {
        this.name = name;
        if (patientId != null) {
            this.patientId = patientId;
            this.idSet = true;
        }
    }

    // JavaBean get/set for patientId (Write-Once enforcement)
    public String getPatientId() {
        return this.patientId;
    }

    public void setPatientId(String id) {
        if (!this.idSet && id != null) {
            this.patientId = id;
            this.idSet = true;
        }
        // Subsequent calls are silently ignored
    }

    // JavaBean get/set for name
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // JavaBean is/set for boolean discharged
    public boolean isDischarged() {
        return this.discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    /**
     * Write-only property: hashed on intake; no getter exists anywhere.
     */
    public void setLockerPin(String pin) {
        if (pin == null || !pin.matches("\\d{4,6}")) {
            return;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pin.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            this.lockerPinHash = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            this.lockerPinHash = String.valueOf(pin.hashCode());
        }
    }
}
