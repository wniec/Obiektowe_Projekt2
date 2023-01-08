package superheroes;

import java.util.Random;

public class SpecialBuilding extends AbstractStaticObject {
    public final boolean isTownHall;
    public final Vector2D position;
    public SpecialBuilding(boolean isTownHall, CityMap map){
        this.isTownHall=isTownHall;
        Random rand = new Random();
        Vector2D v =new Vector2D(rand.nextInt(16) ,rand.nextInt(16));
        while (map.canBePutHere(v)||map.river.isBridge(v)){
            v =new Vector2D(rand.nextInt(16) ,rand.nextInt(16));
        }
        this.position=v;
    }
}
