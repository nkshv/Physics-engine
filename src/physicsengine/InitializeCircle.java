package physicsengine;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class InitializeCircle {
    
    private float x1,x2,x3,x4,x5;
    private float y1,y2,y3,y4,y5;
    private static Vector v1,v2,v3,v4,v5;
    private static Circle c1,c2,c3,c4,c5;
    private float Yvelocity1,Yvelocity2,Yvelocity3,Yvelocity4,Yvelocity5;
    private float Xvelocity1, Xvelocity2, Xvelocity3, Xvelocity4,Xvelocity5;
    private static int numberOfCircles = 0;
   
   public Image initializeCircle(float x, float y, float xv, float yv) {
    Image image = null;
    try {
        image = new Image("assets/ball.png");
        image = image.getScaledCopy(0.5f); // ball 50% smaller
    } catch (SlickException e) {
        e.printStackTrace();
    }
    switch (numberOfCircles) {
        case 0:
            c1 = new Circle(new Vector(x, y), image.getWidth(), image.getHeight(), xv, yv);
            x1 = x;
            y1 = y;
            Yvelocity1 = yv;
            Xvelocity1 = xv;
            break;
        case 1:
            c2 = new Circle(new Vector(x, y), image.getWidth(), image.getHeight(), xv, yv);
            x2 = x;
            y2 = y;
            Yvelocity2 = yv;
            Xvelocity2 = xv;
            break;
        case 2:
            c3 = new Circle(new Vector(x, y), image.getWidth(), image.getHeight(), xv, yv);
            x3 = x;
            y3 = y;
            Yvelocity3 = yv;
            Xvelocity3 = xv;
            break;
        case 3:
            c4 = new Circle(new Vector(x, y), image.getWidth(), image.getHeight(), xv, yv);
            x4 = x;
            y4 = y;
            Yvelocity4 = yv;
            Xvelocity4 = xv;
            break;
        case 4:
            c5 = new Circle(new Vector(x, y), image.getWidth(), image.getHeight(), xv, yv);
            x5 = x;
            y5 = y;
            Yvelocity5 = yv;
            Xvelocity5 = xv;
            break;
        default:
            System.out.println("MAX NUMBER OF BALLS IS 5!");
            break;
    }
    numberOfCircles++;
    return image;
    }

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
                return Float.NaN; // Return NaN if index is out of bounds
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
            return Float.NaN; // Return NaN if index is out of bounds
        }
    }
}
