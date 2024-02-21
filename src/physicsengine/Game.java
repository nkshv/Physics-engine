package physicsengine;
import org.newdawn.slick.*;
import java.util.*;

public class Game extends BasicGame{
    private int numberOfCircles = 0;
    public static float mass, radius;

    static List<Image> imageList = new ArrayList<>();
    static HashMap<Integer, List<Object>> hashMap = new HashMap<>();
  
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException{
        int collisionCount = 0;
        
        for(int i=0; i<numberOfCircles; i++){
            float x = (float) hashMap.get(i).get(0);    float y = (float) hashMap.get(i).get(1);
            System.out.println(x);
            //System.out.println("X: " + x + "   Y: " + y);
            imageList.get(i).draw(x, y); // RENDER BALLS
            for (int j = i + 1; j < numberOfCircles; j++) {
                float x1 = (float) hashMap.get(i).get(0);   float y1 = (float) hashMap.get(i).get(1);
                float xv1 = (float) hashMap.get(i).get(2);  float yv1 = (float) hashMap.get(i).get(3);
                float radius1 = (float) hashMap.get(i).get(4);
                
                float x2 = (float) hashMap.get(j).get(0);   float y2 = (float) hashMap.get(j).get(1);
                float xv2 = (float) hashMap.get(j).get(2);  float yv2 = (float) hashMap.get(j).get(3);
                float radius2 = (float) hashMap.get(j).get(4);
                        
                float[] position1 = {x1, y1};   float[] position2 = {x2, y2};
                

                if (checkCollision(position1, radius1, position2, radius2)){
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

        for (int i = 0; i < numberOfCircles; i++) {
            float x = (float) hashMap.get(i).get(0);    float y = (float) hashMap.get(i).get(1);
            float xv = (float) hashMap.get(i).get(2);   float yv = (float) hashMap.get(i).get(3);
            float radius = (float) hashMap.get(i).get(4);
            
            float[] result_y = freefall(radius, y, yv, delta, Forces.EARTH_GRAVITY);
            float[] result_x = horizontalMove(radius, x, xv, delta);
                        
            List<Object> values = new ArrayList<>();
            values.add(result_x[0]);    values.add(result_y[0]);
            values.add(result_x[1]);    values.add(result_y[1]);
            values.add(radius);
            
            hashMap.put(i, values);
    }
            }
    @Override
    public void init(GameContainer gc) throws SlickException{
        initializeCircle(0, 0, 14, 0, 56); // (X, Y, X-VELOCITY, Y-VELOCITY, RADIUS)
        initializeCircle(222, 0, 20, 0, 56);
        initializeCircle(600, 0, 30, 0, 56);
        initializeCircle(900, 0, 20, 0, 56);
        initializeCircle(1200, 0, 24, 0, 56);
        initializeCircle(700, 0, 30, 0, 56);
    }
   
    private Image initializeCircle(float x, float y, float xv, float yv, float radius) {
      Image image = null;
      try {
          List<Object> values = new ArrayList<>();
          values.add(x);
          values.add(y);
          values.add(xv);
          values.add(yv);
          values.add(radius);
          hashMap.put(numberOfCircles, values);
          
          if ((numberOfCircles + 1) <=5){
            String filePath = "assets" + "/ball" + (numberOfCircles + 1) + ".png";
            image = new Image(filePath);
            image = image.getScaledCopy(radius / 112.0f);
            imageList.add(image);
            System.out.println((numberOfCircles + 1) + "   -  " + filePath);
          }
          else{
            String filePath = "assets" + "/ball" + ".png";
            image = new Image(filePath);
            image = image.getScaledCopy(radius / 112.0f);
            imageList.add(image);
            System.out.println((numberOfCircles + 1) + "   -  " + filePath);
          }
      } catch (SlickException e) {
          e.printStackTrace();
      }
      numberOfCircles++;
      return image;
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
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Array");
        }
        return collision;
    }
    
    public float[] collisionResolution(float[] position1, float Xv, float mass1, float[] position2, float Yv, float mass2){
        float x1 = position1[0]; float y1 = position1[1];
        float x2 = position2[1]; float y2 = position2[1];
        
        // -> x1, y1, x2, y2, Xv, Yv, mass1, mass2 <-
        
        
        return new float[]{};
    }
        private static void printHashMap() {
        for (Map.Entry<Integer, List<Object>> entry : hashMap.entrySet()) {
            Integer key = entry.getKey();
            List<Object> values = entry.getValue();
            System.out.print("Key: " + key + ", ");
            System.out.print("Values: ");
            for (Object value : values) {
                System.out.print(value + " ");
            }
            System.out.println();
        } 
    }

    public Game(){
        super("Physics Engine");
    }
}
