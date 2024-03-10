package physicsengine;
import org.newdawn.slick.*;
import java.util.*;

public class Game extends BasicGame{
    private int numberOfCircles = 0;
    public static float mass, radius;
    public static int frameCount = 0;
    int[] collisionData = {-1};


    static List<Image> imageList = new ArrayList<>();
    static HashMap<Integer, List<Object>> hashMap = new HashMap<>();

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException{
        int collisionCount = 0;
        frameCount++;
        
        for(int i=0; i<numberOfCircles; i++){
            boolean realCollision = true;
            float x = (float) hashMap.get(i).get(0);    float y = (float) hashMap.get(i).get(1);
            imageList.get(i).draw(x, y); // RENDER BALLS
            for (int j = i + 1; j < numberOfCircles; j++) {
                float x1 = (float) hashMap.get(i).get(0);   float y1 = (float) hashMap.get(i).get(1);
                float xv1 = (float) hashMap.get(i).get(2);  float yv1 = (float) hashMap.get(i).get(3);
                float radius1 = (float) hashMap.get(i).get(4);
                
                float x2 = (float) hashMap.get(j).get(0);   float y2 = (float) hashMap.get(j).get(1);
                float xv2 = (float) hashMap.get(j).get(2);  float yv2 = (float) hashMap.get(j).get(3);
                float radius2 = (float) hashMap.get(j).get(4);
                        
                float[] position1 = {x1, y1};   float[] position2 = {x2, y2};
                
                if (collisionData[0] != -1){
                    int ic = collisionData[0];
                    int jc = collisionData[1];
                    int fc = collisionData[2];
                    if (ic == i && jc == j && fc == frameCount-1){
                        realCollision = false;
                    }
                }
                

                if (checkCollision(position1, radius1, position2, radius2)){
                    collisionData = new int[]{i, j, frameCount};                    
                    if(realCollision){
                        List<Object> new_values = new ArrayList<>();
                        List<Object> new_values1 = new ArrayList<>();
                        float[] collision_response = collisionResolution(position1, xv1, yv1, radius1, position2, xv2, yv2, radius2);
                        float newxv1 = collision_response[0];        float newyv1 = collision_response[1];
                        float newxv2 = collision_response[2];        float newyv2 = collision_response[3];

                        new_values.add(x1); new_values.add(y1); new_values.add(newxv1); new_values.add(newyv1); new_values.add(radius1);
                        new_values1.add(x2); new_values1.add(y2); new_values1.add(newxv2); new_values1.add(newyv2); new_values1.add(radius2);

                        hashMap.put(i, new_values);
                        hashMap.put(j, new_values1);

                        collisionCount++;
                    }
                    
                }
            }
        }
    }
    @Override
    public void update(GameContainer gc, int t) throws SlickException {
                
        float delta = t /120.f; // converts t to seconds

        for (int i = 0; i < numberOfCircles; i++) {
            float x = (float) hashMap.get(i).get(0);    float y = (float) hashMap.get(i).get(1);
            float xv = (float) hashMap.get(i).get(2);   float yv = (float) hashMap.get(i).get(3);
            float radius = (float) hashMap.get(i).get(4);
            
            float[] result_y = freefall(radius, y, yv, delta, Forces.EARTH_GRAVITY * 2);
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
        initializeCircle(0, 0, 300, 0, 56); // (X, Y, X-VELOCITY, Y-VELOCITY, RADIUS)
        initializeCircle(1500, 0, 0, 0, 56);
        
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
        if (y >= 1000 - 2*radius ) { 
            Yvelocity *= -1;
        }
        Yvelocity += GRAVITY * delta;

        y += Yvelocity * delta + 0.5f * GRAVITY * delta * delta;

        if (y > 1000 - 2*radius) {
            y = 1000 - 2*radius;
        }
        return new float[]{y, Yvelocity};
    }

    public float[] horizontalMove(float radius, float x, float Xvelocity, float delta){
        if (x >= 1800 - 2 * radius){
             Xvelocity = Math.abs(Xvelocity) * -0.7f;
        }
        if (x < 0){
            Xvelocity = Math.abs(Xvelocity) * 0.7f;
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
    
    public float[] collisionResolution(float[] position1, float Xv1, float Yv1, float radius1,
                                       float[] position2, float Xv2, float Yv2, float radius2) {
        float relVelX = Xv2 - Xv1;
        float relVelY = Yv2 - Yv1;
        
        float mass1 = 3.14f * radius1 * radius1;
        float mass2 = 3.14f * radius2 * radius2;
                
        float dx = position2[0] - position1[0];
        float dy = position2[1] - position1[1];
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        float nx = dx / distance;
        float ny = dy / distance;

        float relVelDotNorm = relVelX * nx + relVelY * ny;

        float impulseScalar = 2 * relVelDotNorm /
                              (1 / mass1 + 1 / mass2);

        float newVelX1 = Xv1 + (impulseScalar / mass1) * nx;
        float newVelY1 = Yv1 + (impulseScalar / mass1) * ny;
        float newVelX2 = Xv2 - (impulseScalar / mass2) * nx;
        float newVelY2 = Yv2 - (impulseScalar / mass2) * ny;

        return new float[]{newVelX1, newVelY1, newVelX2, newVelY2};
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