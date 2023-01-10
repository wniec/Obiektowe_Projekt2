package superheroes.characters;

import javafx.scene.image.Image;
import superheroes.*;

public abstract class AbstractSuperhero {
    public abstract int getTime(Problem problem);
    public abstract Image getImage();
    protected int maxDistance;
    protected int availableDistance;
    protected boolean isBusy;
    protected boolean hasHelicopter;
    protected Vector2D position;
    protected int helicopterDays;
    public boolean cannotGoTo(Vector2D position,CityMap map) {
        AbstractStaticObject object;
        if(position.follows(new Vector2D(0,0))&&position.precedes(new Vector2D(15,15))&&!map.river.isRiver(position))
        {
            object = map.objectHere(position);
            AbstractSuperhero heroHere=map.heroAt(position);
            return ( object instanceof Obstacle) && ((Obstacle)object).isSevere ||((heroHere!=null)&&!position.equals(this.position));
        }
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
    public void setPosition(Vector2D v){this.position=v;}
    public Vector2D getPosition(){return this.position;}

    public void move(Vector2D v,int steps) {
        this.position=v;
        this.availableDistance-=steps;
    }
}
