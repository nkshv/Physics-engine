package physicsengine;
import org.newdawn.slick.*;

public class Game extends BasicGame{
   
    private static Vector v1,v2,v3,v4,v5;
    private static Circle c1,c2,c3,c4,c5;
    
    private Image a,b,c,d,e;
    private Sound oof, thud;
    private float x1,x2,x3,x4,x5; //NOT THE CENTER OF THE CIRCLE
    private float y1,y2,y3,y4,y5; //NOT THE CENTER OF THE CIRCLE
    private int numberOfCircles = 0;
    public static float mass, radius;
    
    private float Yvelocity1,Yvelocity2,Yvelocity3,Yvelocity4,Yvelocity5;
    private float Xvelocity1, Xvelocity2, Xvelocity3, Xvelocity4,Xvelocity5;
    
    private Image[] Image_Array;
    

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException{
        
        float[][] allPositions = {{getX(0), getY(0)}, {getX(1), getY(1)}, {getX(2), getY(2)}, {getX(3), getY(3)}, {getX(4), getY(4)}};
        float[] radii = {c1 != null ? c1.getRadius() : 0, c2 != null ? c2.getRadius() : 0, c3 != null ? c3.getRadius() : 0, c4 != null ? c4.getRadius() : 0, c5 != null ? c5.getRadius() : 0};        // FREE FALL + HORIZONTAL MOVEMENT IMPLEMENTATION

        int collisionCount = 0;

        for(int i=0; i<numberOfCircles; i++){
            Image_Array[i].draw(getX(i), getY(i)); // RENDER BALLS
            for (int j = i + 1; j < numberOfCircles; j++) {
                float[] position1 = {allPositions[i][0], allPositions[i][1]};
                float[] position2 = {allPositions[j][0], allPositions[j][1]};

                if (checkCollision(position1, radii[i], position2, radii[j])){
                    int yCoordinate = 50 + (collisionCount * 20); // for drawString purposes
                    g.drawString("COLLISION HAPPENED BETWEEN " + (i + 1) + " AND " + (j + 1) + "!", 100, yCoordinate);
                    collisionCount++; // Increment the collision count
                }
            }
        }
    }
    @Override
    public void update(GameContainer gc, int t) throws SlickException {
        float delta = t /60.f; // converts t to seconds
        float[] xPositions = {x1, x2, x3, x4, x5};
        float[] yPositions = {y1, y2, y3, y4, y5};
        float[] Xvelocities = {Xvelocity1, Xvelocity2, Xvelocity3, Xvelocity4, Xvelocity5};
        float[] Yvelocities = {Yvelocity1, Yvelocity2, Yvelocity3, Yvelocity4, Yvelocity5};
        float[] radii = {c1 != null ? c1.getRadius() : 0, c2 != null ? c2.getRadius() : 0, c3 != null ? c3.getRadius() : 0, c4 != null ? c4.getRadius() : 0, c5 != null ? c5.getRadius() : 0};        // FREE FALL + HORIZONTAL MOVEMENT IMPLEMENTATION
        for (int i = 0; i < numberOfCircles; i++) {
            float[] result_y = freefall(radii[i], yPositions[i], Yvelocities[i], delta, Forces.MOON_GRAVITY);
            float[] result_x = horizontalMove(radii[i], xPositions[i], Xvelocities[i], delta);
        
            yPositions[i] = result_y[0]; Yvelocities[i] = result_y[1];
            xPositions[i] = result_x[0]; Xvelocities[i] = result_x[1];
    }
        y1 = yPositions[0]; y2 = yPositions[1]; y3 = yPositions[2]; y4 = yPositions[3]; y5 = yPositions[4];
        x1 = xPositions[0]; x2 = xPositions[1]; x3 = xPositions[2]; x4 = xPositions[3]; x5 = xPositions[4];
        
        Yvelocity1 = Yvelocities[0]; Yvelocity2 = Yvelocities[1]; Yvelocity3 = Yvelocities[2]; Yvelocity4 = Yvelocities[3]; Yvelocity5 = Yvelocities[4];
        Xvelocity1 = Xvelocities[0]; Xvelocity2 = Xvelocities[1]; Xvelocity3 = Xvelocities[2]; Xvelocity4 = Xvelocities[3]; Xvelocity5 = Xvelocities[4];
            }
    @Override
    public void init(GameContainer gc) throws SlickException{
        a = initializeCircle(0, 0, 14, 0, 100); // (X, Y, X-VELOCITY, Y-VELOCITY, RADIUS)
        b = initializeCircle(222, 0, 20, 0, 30);
        c = initializeCircle(600, 0, 30, 0, 25);
        //d = initializeCircle(900, 0, 2, 0, 5);
        //e = initializeCircle(1200, 0, 1, 0, 5);

        Image_Array = new Image[]{a, b, c, d, e};
    }
   
  
    private Image initializeCircle(float x, float y, float xv, float yv, float radius) {
        Image image = null;
        try {
            switch (numberOfCircles) {
                case 0:
                    x1 = x;
                    y1 = y;
                    Yvelocity1 = yv;
                    Xvelocity1 = xv;
                    image = new Image("assets/ball1.png");
                    image = image.getScaledCopy(radius/112.0f); //ball scaled to radius "radius"
                    c1 = new Circle(new Vector(x, y), mass, radius, xv, yv);
                    break;
                case 1:
                    x2 = x;
                    y2 = y;
                    Yvelocity2 = yv;
                    Xvelocity2 = xv;
                    image = new Image("assets/ball2.png");
                    image = image.getScaledCopy(radius/112.0f);
                    c2 = new Circle(new Vector(x, y), mass, radius, xv, yv);
                    break;
                case 2:
                    x3 = x;
                    y3 = y;
                    Yvelocity3 = yv;
                    Xvelocity3 = xv;
                    image = new Image("assets/ball3.png");
                    image = image.getScaledCopy(radius/112.0f);
                    c3 = new Circle(new Vector(x, y), mass, radius, xv, yv);
                    break;
                case 3:
                    x4 = x;
                    y4 = y;
                    Yvelocity4 = yv;
                    Xvelocity4 = xv;
                    image = new Image("assets/ball1.png");
                    image = image.getScaledCopy(radius/112.0f);
                    c4 = new Circle(new Vector(x, y), mass, radius, xv, yv);
                    break;
                case 4:
                    x5 = x;
                    y5 = y;
                    Yvelocity5 = yv;
                    Xvelocity5 = xv;
                    image = new Image("assets/ball1.png");
                    image = image.getScaledCopy(radius/112.0f);
                    c5 = new Circle(new Vector(x, y), mass, radius, xv, yv);
                    break;
                default:
                    System.out.println("No image defined for this case!");
                    break;
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
        numberOfCircles++;
        return image;
    }
   
    //change it to an array so that it returns every useful variable
    public float getY(int circleIndex) {
        switch (circleIndex) {
            case 0:
                return y1;
            case 1:
                return y2;
            case 2:
                return y3;
            case 3:
                return y4;
            case 4:
                return y5;
            default:
                return Float.NaN;
        }
    }
    public float getX(int circleIndex) {
    switch (circleIndex) {
        case 0:
            return x1;
        case 1:
            return x2;
        case 2:
            return x3;
        case 3:
            return x4;
        case 4:
            return x5;
        default:
            return Float.NaN; 
    }
}   
    
    public float[] freefall(float radius, float y, float Yvelocity, float delta, float GRAVITY) {
        if (y >= 768 - 2*radius ) { 
            Yvelocity *= -1;
        }
        Yvelocity += GRAVITY * delta;

        y += Yvelocity * delta + 0.5f * GRAVITY * delta * delta;

        if (y > 768 - 2*radius) {
            y = 768 - 2*radius;
        }
        return new float[]{y, Yvelocity};
    }

    public float[] horizontalMove(float radius, float x, float Xvelocity, float delta){
        if (x >= 1366 - 2 * radius){
             Xvelocity = Math.abs(Xvelocity) * -0.9f;
        }
        if (x < 0){
            Xvelocity = Math.abs(Xvelocity) * 0.9f;
        }
        x += Xvelocity * delta;
        return new float[]{x, Xvelocity};
    }
    
    public boolean checkCollision(float[] position1, float radius1, float[] position2, float radius2) {
        boolean collision = false;
        try {
            float x1 = position1[0] + radius1; float y1 = position1[1] + radius1;
            float x2 = position2[0] + radius2; float y2 = position2[1] + radius2;
            
            float h = Math.abs(x1 - x2); //height
            float b = Math.abs(y1 - y2); //base
            float distance = (float) Math.sqrt(h*h + b*b);

            if (distance <= radius1 + radius2){
                collision = true;
                System.out.println(distance + " --- " + h + "----" + b);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Array");
        }
        return collision;
    }
    
    public float[] collisionResolution(float[] position1, float Xv, float mass1, float[] position2, float Yv, float mass2){
        float x1 = position1[0]; y1 = position1[1];
        float x2 = position2[1]; y2 = position2[1];
        
        // -> x1, y1, x2, y2, Xv, Yv, mass1, mass2 <-
        
        
        return new float[]{};
    }

    public Game(){
        super("Physics Engine");
    }
}