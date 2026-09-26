package class_problems;
abstract class Vehicle {
    String name; boolean isAvailable = true;
    public Vehicle(String name) { this.name = name; }
    abstract double calculateCharge(int days);
}

class LuxuryCar extends Vehicle {
    public LuxuryCar(String name) { super(name); }
    double calculateCharge(int days) { return days * 100.0; }
}

class StandardCar extends Vehicle {
    public StandardCar(String name) { super(name); }
    double calculateCharge(int days) { return days * 50.0; }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        LuxuryCar v1 = new LuxuryCar("Luxury Car A");
        StandardCar v2 = new StandardCar("Standard Car B");

        System.out.printf("%s rented for 3 days. Total charge: $%.2f.\n", v1.name, v1.calculateCharge(3));
        v1.isAvailable = false;
        System.out.printf("%s rented for 5 days. Total charge: $%.2f.\n", v2.name, v2.calculateCharge(5));
        v2.isAvailable = false;

        v1.isAvailable = true;
        System.out.println(v1.name + " returned. Now available.");
    }
}
