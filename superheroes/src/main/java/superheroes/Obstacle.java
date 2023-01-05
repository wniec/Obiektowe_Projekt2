package superheroes;

import java.util.Random;

public class Obstacle {
    public final Vector2D position;
    public final boolean isSevere;
    public Obstacle(boolean isSevere, CityMap map){
        this.isSevere =isSevere;
        Random rand = new Random();
        Vector2D v =new Vector2D(rand.nextInt(16) ,rand.nextInt(16));
        while (map.anyObstacleHere(v)){
            v =new Vector2D(rand.nextInt(16) ,rand.nextInt(16));
        }
        this.position=v;
    }
}
