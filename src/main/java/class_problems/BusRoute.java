package class_problems;
import java.util.Arrays;

public class BusRoute implements Comparable<BusRoute> {
    private final String routeCode;
    private final String routeName;
    private final int priority;

    // Full constructor
    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    // Constructor chaining with default priority (defaults to 1)
    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 1);
    }

    public String getRouteCode() {
        return routeCode;
    }

    public String getRouteName() {
        return routeName;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(BusRoute other) {
        if (other == null) return -1;

        // 1. Priority descending (higher priority ranked first)
        int pCompare = Integer.compare(other.priority, this.priority);
        if (pCompare != 0) {
            return pCompare;
        }

        // 2. Case-insensitive routeCode ascending
        int codeCompare = this.routeCode.compareToIgnoreCase(other.routeCode);
        if (codeCompare != 0) {
            return codeCompare;
        }

        // 3. Case-insensitive routeName ascending
        return this.routeName.compareToIgnoreCase(other.routeName);
    }

    // Deterministic in-place stable sort (Insertion Sort)
    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        if (routes == null || routes.length <= 1) {
            return routes;
        }

        BusRoute[] sorted = Arrays.copyOf(routes, routes.length);
        for (int i = 1; i < sorted.length; i++) {
            BusRoute key = sorted[i];
            int j = i - 1;

            while (j >= 0 && sorted[j].compareTo(key) > 0) {
                sorted[j + 1] = sorted[j];
                j = j - 1;
            }
            sorted[j + 1] = key;
        }
        return sorted;
    }

    public static void main(String[] args) {
        BusRoute[] routes = {
                new BusRoute("RT205L", "Airport Express", 3),
                new BusRoute("rt201j", "City Central", 4),
                new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(routes);
        for (BusRoute r : ranked) {
            System.out.print("\"" + r.getRouteCode() + "\" ");
        }
        System.out.println();
    }
}
