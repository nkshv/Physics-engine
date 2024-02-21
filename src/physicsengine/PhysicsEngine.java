package physicsengine;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class PhysicsEngine {

    private static Vector v1;
    private static Vector v2;
    private static Vector v3;
    private static Vector v4;

    public static void main(String[] args) {


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
