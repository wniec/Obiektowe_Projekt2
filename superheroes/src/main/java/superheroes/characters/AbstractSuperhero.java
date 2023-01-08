package superheroes.characters;

import superheroes.*;

public abstract class AbstractSuperhero {
    public abstract int getTime(Problem problem);
    public Problem problem=null;
    protected int maxDistance;
    protected int availableDistance=maxDistance;
    protected boolean isBusy;
    protected boolean hasHelicopter;
    protected int helicopterDays;
    public boolean cannotGoTo(Vector2D position,CityMap map) {
        if(position.follows(new Vector2D(0,0))&&position.precedes(new Vector2D(15,15))&&!map.river.isRiver(position))
            return (map.objectsHere(position) instanceof Obstacle) && ((Obstacle)map.objectsHere(position)).isSevere;
        return true;
    }
    public int getMaxDistance(){
        return this.maxDistance;
    }

    public int getAvailableDistance() {
        return availableDistance;
    }

    public boolean HasHelicopter() {
        return hasHelicopter;
    }
    public void tick(){
        availableDistance=maxDistance;
        helicopterDays--;
    }
}
