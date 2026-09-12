package assigment_problems;
import java.util.LinkedHashMap;
import java.util.Map;

class AccessCheckerProblem1 {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "public":
                return "ALLOWED";
            case "protected":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";
            case "default":
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

    public static String summarizeByModifier(String[][] attempts) {
        String[] order = {"private", "default", "protected", "public"};
        Map<String, int[]> counts = new LinkedHashMap<>();
        for (String mod : order) {
            counts.put(mod, new int[]{0, 0}); // index 0: allowed, index 1: denied
        }

        for (String[] attempt : attempts) {
            String mod = attempt[0];
            String ctx = attempt[1];
            String result = classifyAccess(mod, ctx);
            if (counts.containsKey(mod)) {
                if ("ALLOWED".equals(result)) {
                    counts.get(mod)[0]++;
                } else {
                    counts.get(mod)[1]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < order.length; i++) {
            String mod = order[i];
            int[] c = counts.get(mod);
            sb.append(mod).append(": ").append(c[0]).append(" allowed / ").append(c[1]).append(" denied");
            if (i < order.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }
}

class LibraryMember {
    // Appropriate field visibility levels
    private String membershipId;
    String branchCode;         // package-private / default
    protected double finesOwed;
    public String displayName;

    // No usable no-arg constructor provided
    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().length() < 4) {
            throw new IllegalArgumentException("membershipId must be non-blank and at least 4 characters.");
        }
        this.membershipId = membershipId.trim();
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    public String getMembershipId() {
        return membershipId;
    }
}
