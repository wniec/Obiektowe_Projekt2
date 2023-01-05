package superheroes;

import java.util.ArrayList;
import java.util.HashMap;

public class CityMap {
    private final int width = 16;
    private final int height = 16;
    private final River river;
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();

    public CityMap() {
        this.river = new River();
        for(int i=0;i<8;i++){
            placeObstacle(new Obstacle(false,this));
        }
        for(int i=0;i<16;i++){
            placeObstacle(new Obstacle(true,this));
        }
    }

    public boolean anyObstacleHere(Vector2D position) {
        if (river.isRiver(position))
            return false;
        if (river.isBridge(position))
            return false;
        for (Obstacle obstacle : obstacles) {
            if (obstacle.position.equals(position))
                return false;
        }
        return true;
    }
    private void placeObstacle(Obstacle obstacle){
        this.obstacles.add(obstacle);

    }
}
