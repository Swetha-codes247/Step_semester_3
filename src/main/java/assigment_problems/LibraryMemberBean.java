package assigment_problems;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class LibraryMemberBean {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String hashedSecurityAnswer; // write-only: stored one-way, no getter exists

    // Chained Constructor 1: no-arg
    public LibraryMemberBean() {
        this(null, null);
    }

    // Chained Constructor 2: name-only
    public LibraryMemberBean(String name) {
        this(null, name);
    }

    // Chained Constructor 3: id + name (Master initialization path)
    public LibraryMemberBean(String membershipId, String name) {
        this.membershipId = membershipId;
        this.name = name;
        this.premiumMember = false;
    }

    // JavaBean: getMembershipId & write-once setMembershipId
    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (this.membershipId == null) {
            this.membershipId = id;
        }
        // Subsequent calls are silently ignored
    }

    // JavaBean: name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // JavaBean: boolean naming convention (isX / setX)
    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    // Write-only property: deterministic one-way SHA-256 transformation
    public void setSecurityAnswer(String answer) {
        if (answer == null) {
            this.hashedSecurityAnswer = null;
            return;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(answer.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            this.hashedSecurityAnswer = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            this.hashedSecurityAnswer = String.valueOf(answer.hashCode());
        }
    }
}
