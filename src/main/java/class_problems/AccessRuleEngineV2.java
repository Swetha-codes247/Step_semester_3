package class_problems;
class AccessRuleEngineV2 {

    /**
     * Classifies access considering cross-package inheritance rules for 5 distinct contexts.
     */
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier) {
            case "public":
                return "ALLOWED";

            case "private":
                return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";

            case "default":
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext))
                        ? "ALLOWED" : "DENIED";

            case "protected":
                switch (accessorContext) {
                    case "SAME_CLASS":
                    case "SAME_PACKAGE":
                    case "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE":
                        return "ALLOWED";
                    case "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE":
                    case "DIFFERENT_PACKAGE":
                    default:
                        // Java checks compile-time reference type for cross-package protected access
                        return "DENIED";
                }

            default:
                return "DENIED";
        }
    }

    /**
     * Converts underscore-separated codes into title-cased readable phrases.
     * e.g., "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE" -> "Subclass Different Package Parent Type"
     */
    public static String describeContext(String accessorContext) {
        if (accessorContext == null || accessorContext.trim().isEmpty()) {
            return "";
        }

        String[] words = accessorContext.trim().split("_+");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1));
                if (i < words.length - 1) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }
}
