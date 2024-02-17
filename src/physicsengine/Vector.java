package physicsengine;

public class Vector {
    private float x;
    private float y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    // No parameters -- (0,0) vector
    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    public float getX() {
        return x;
    }

    public float getY() {
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

    public void multiply(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public float magnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        float mag = magnitude();
        if (mag != 0) {
            this.x /= mag;
            this.y /= mag;
        }
    }

    public float dot(Vector other) {
        return this.x * other.x + this.y * other.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
