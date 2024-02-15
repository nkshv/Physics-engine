package physicsengine;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    //no paramethers -- (0,0) vector
    public Vector (){
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void add(Vector other) {
        this.x += other.x;
        this.y += other.y;
    }

    public void subtract(Vector other) {
        this.x -= other.x;
        this.y -= other.y;
    }

    public void multiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        double mag = magnitude();
        if (mag != 0) {
            this.x /= mag;
            this.y /= mag;
        }
    }

    public double dot(Vector other) {
        return this.x * other.x + this.y * other.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
