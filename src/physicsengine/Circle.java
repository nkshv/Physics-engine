package physicsengine;

public class Circle {
    
    private Vector position;
    private float mass;
    private float area;
    private float density;
    private float Yv0;
    private float Xv0;
    
    private float radius;

    
    // Constructor for Circle
    public Circle(Vector position, float mass, float radius, float Yv0, float Xv0) {
        this.position = position;
        this.mass = mass;
        this.radius = radius;
        this.area = (float) (Math.PI * radius * radius);
        this.Yv0 = Yv0;
        this.Xv0 = Xv0;
    }
    
    
    public Vector getPosition() {
        return position;
    }

    public float getMass() {
        return mass;
    }

    public float getArea() {
        return area;
    }

    public float getDensity() {
        return density;
    }

    public float getRadius() {
        return radius;
    }

    public float getY() {
        return position.getY();
    }

    public float getX() {
        return position.getX();
    }
    
    public float getYv0(){
        return Yv0;
    }
    
    public float getXv0(){
        return Xv0;
    }
}
