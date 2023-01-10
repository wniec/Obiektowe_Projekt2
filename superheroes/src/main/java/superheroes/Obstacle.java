package superheroes;

import java.util.Random;

public class Obstacle extends AbstractStaticObject {
    public final boolean isSevere;
    public Obstacle(boolean isSevere, CityMap map){
        this.isSevere =isSevere;
        Random rand = new Random();
        Vector2D v =new Vector2D(rand.nextInt(16) ,rand.nextInt(16));
        while (map.cannotBePutHere(v) ||map.river.isBridge(v)){
            v =new Vector2D(rand.nextInt(16) ,rand.nextInt(16));
        }
        this.position=v;
    }
}
