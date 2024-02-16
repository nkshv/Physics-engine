package physicsengine;
import org.newdawn.slick.*;

public class Game extends BasicGame{
    
    private Image i;
    private float x,y;
   
    float rotationSpeed = 5.0f;

    private float Yvelocity;
    
    // GRAVITY OF PLANETS
    private static final float EARTH_GRAVITY = 9.8f;
    private static final float MOON_GRAVITY = 1.62f;
    private static final float MARS_GRAVITY = 3.71f;
    private static final float SUN_GRAVITY = 274.0f;
    private static final float JUPITER_GRAVITY = 274.0f;    

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException{
        i.draw(x,y);

    }
    
    @Override
    public void update(GameContainer gc, int t) throws SlickException {
        float delta = t /60.f; // converts t to seconds
        
        y = freefall(delta, EARTH_GRAVITY);
        i.rotate(rotationSpeed);

            }
    
    @Override
    public void init(GameContainer gc) throws SlickException{
        i = new Image("assets/ball.png");
        i = i.getScaledCopy(0.5f); // ball 50% smaller
        
        Yvelocity = 0;       
        x = 0;
        y = 0;
    }
   
    public float freefall(float delta, float GRAVITY) {
        if (y >= 490f) { // CHANGE VALUE LATER
            Yvelocity *= -1;
            rotationSpeed = rotationSpeed/1.5f; // CHANGE VALUE LATER
        }
        Yvelocity += GRAVITY * delta;

        y += Yvelocity * delta + 0.5f * GRAVITY * delta * delta;

        if (y > 490f) {
            y = 490f;
        }
        return y;
    }

    public Game(){
        super("Physics Engine");
    }
}
