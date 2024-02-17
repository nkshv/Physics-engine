package physicsengine;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class PhysicsEngine {

    private static Vector v1;
    private static Vector v2;
    private static Vector v3;
    private static Vector v4;

    public static void main(String[] args) {
        v1 = new Vector(0, 10);
        v2 = new Vector(10, 0);
        v3 = new Vector();
        v4 = new Vector(5, 5);


        Circle c1 = new Circle(v2, 5, 5, 0, 0);
        
        System.out.println(c1.getX());
        System.out.println(c1.getY());

        try {
            AppGameContainer app = new AppGameContainer(new Game());
            app.setDisplayMode(1366, 768, false); // .... ... .. ..
            app.setTargetFrameRate(60); 
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
