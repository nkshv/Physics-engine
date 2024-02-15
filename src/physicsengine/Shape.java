package physicsengine;

public class Shape {
    
    private boolean shape; // 0=circle; 1=square.
    private Vector position;
    private double mass;
    private double area;
    private double density;
    
    private double radius;
    
    private double width;
    private double height;
    
    //Constructor for Circle
    public Shape(Vector position, double mass, double density, double radius) {
        this.shape = false; // Circle
        this.position = position;
        this.mass = mass;
        this.density = density;
        this.radius = radius;
        this.area = Math.PI * radius * radius;
    }
    
    //Constructor for Square
    public Shape(Vector position, double mass, double density, double width, double height) {
        this.shape = true; // Square
        this.width = width;
        this.height = height;
        this.position = position;
        this.mass = mass;
        this.density = density;
        this.area = width * height;
    }

     // Getters
    public boolean isCircle() {
        return !shape;
    }

    public boolean isSquare() {
        return shape;
    }

    public Vector getPosition() {
        return position;
    }

    public double getMass() {
        return mass;
    }

    public double getArea() {
        return area;
    }

    public double getDensity() {
        return density;
    }

    public double getRadius() {
        return radius;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
