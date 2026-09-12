package class_problems;
import java.util.Arrays;

class PatientVitals {
    private static final int MAX_READINGS = 500;
    private final double[] readings;
    private int count;

    public PatientVitals(double[] initialReadings) {
        this.readings = new double[MAX_READINGS];
        this.count = 0;

        // Reuse recordReading to prevent duplicate validation rules
        if (initialReadings != null) {
            for (double reading : initialReadings) {
                recordReading(reading);
            }
        }
    }

    /**
     * Silently rejects readings <= 0 or > 45.0 degrees Celsius.
     */
    public void recordReading(double reading) {
        if (reading <= 0.0 || reading > 45.0) {
            return; // Silently reject out-of-range readings
        }
        if (count < MAX_READINGS) {
            readings[count++] = reading;
        }
    }

    public double getAverage() {
        if (count == 0) {
            return 0.0;
        }
        double sum = 0.0;
        for (int i = 0; i < count; i++) {
            sum += readings[i];
        }
        return sum / count;
    }

    /**
     * Returns a defensive copy to prevent external state tampering.
     */
    public double[] getAllReadings() {
        return Arrays.copyOf(readings, count);
    }
}
