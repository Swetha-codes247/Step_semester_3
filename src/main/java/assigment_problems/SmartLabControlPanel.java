package assigment_problems;
import java.util.*;

interface Capability { String getName(); }

class PowerCapability implements Capability {
    boolean isOn = false;
    public String getName() { return "Power"; }
}

class BrightnessCapability implements Capability {
    int brightness = 0;
    public String getName() { return "Brightness"; }
    public void setBrightness(int val) {
        if (val >= 0 && val <= 100) brightness = val;
        else throw new IllegalArgumentException("Invalid brightness");
    }
}

class TemperatureCapability implements Capability {
    int temp = 20;
    public String getName() { return "Temperature"; }
    public void setTemp(int val) {
        if (val >= 16 && val <= 30) temp = val;
        else System.out.println("Rejected: Lab AC temperature must be between 16°C and 30°C");
    }
}

class Device {
    String name;
    Map capabilities = new HashMap<>();
    public Device(String name, Capability... caps) {
        this.name = name;
        for (Capability c : caps) capabilities.put(c.getName(), c);
    }
}

public class SmartLabControlPanel {
    public static void main(String[] args) {
        Device labAC = new Device("Lab AC", new PowerCapability(), new TemperatureCapability());
        Device lights = new Device("Ceiling Lights", new PowerCapability(), new BrightnessCapability());
        Device projector = new Device("Projector", new PowerCapability());

        System.out.println("Scene 'Lecture Mode' started.");
        for (Device d : Arrays.asList(labAC, lights, projector)) {
            ((PowerCapability) d.capabilities.get("Power")).isOn = true;
            System.out.println(d.name + ": ON.");
        }
        ((BrightnessCapability) lights.capabilities.get("Brightness")).setBrightness(40);
        System.out.println("Ceiling Lights: brightness set to 40%.");
        ((TemperatureCapability) labAC.capabilities.get("Temperature")).setTemp(24);
        System.out.println("Lab AC: temperature set to 24°C.");
        System.out.println("Scene 'Lecture Mode' completed: 5 actions applied.");

        // Admin sets temperature to 12°C
        ((TemperatureCapability) labAC.capabilities.get("Temperature")).setTemp(12);

        // Add brightness to projector
        BrightnessCapability pb = new BrightnessCapability();
        pb.setBrightness(70);
        projector.capabilities.put("Brightness", pb);
        System.out.println("Projector: Brightness capability added.");
        System.out.println("Projector: brightness set to 70%.");
    }
}
