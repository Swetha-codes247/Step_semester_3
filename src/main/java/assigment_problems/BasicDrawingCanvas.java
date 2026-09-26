package assigment_problems;
abstract class Shape {
    private static int counter = 1001;
    private final String shapeId;

    public Shape() {
        this.shapeId = "SHAPE-" + counter++;
    }

    public String getShapeId() {
        return shapeId;
    }

    public abstract double calculateArea();
    public abstract void scale(double factor);
    public abstract void scale(double xFactor, double yFactor);
}

class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        super();
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double factor) {
        this.radius *= factor;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        // Average scale factor for a radial circle
        this.radius *= (xFactor + yFactor) / 2.0;
    }
}

class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        super();
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double factor) {
        this.side *= factor;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        this.side *= xFactor;
    }
}

public class BasicDrawingCanvas {
    public static void printArea(Shape s) {
        System.out.printf("%.2f%n", s.calculateArea());
    }

    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        System.out.printf("~%.2f%n", c.calculateArea());

        SquareShape sq = new SquareShape(4.0);
        System.out.println(sq.calculateArea());

        sq.scale(2.0);
        System.out.println(sq.calculateArea());

        printArea(c);
    }
}
