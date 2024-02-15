package physicsengine;
import org.newdawn.slick.*;

public class Game extends BasicGame{
    
    private Image i;
    private float x,y;
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException{
        i.draw(x,y);
    }
    
    @Override
    public void update(GameContainer gc, int t) throws SlickException{
        
        x+= 2;
        y+= 2;
        
        i.rotate(t/10);
        
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException{
        i = new Image("assets/ball.png");
        
        x = 0;
        y = 0;
    }
    
    
    public Game(){
        super("Game");
    }
}
