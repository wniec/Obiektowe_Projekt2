package superheroes.characters;

import javafx.scene.image.Image;
import superheroes.*;

import java.util.ArrayList;

public class SuperFireman extends AbstractSuperhero{
    public SuperFireman(){
        this.isBusy=false;
        this.maxDistance=4;
        this.availableDistance=4;
        this.hasHelicopter=false;
    }
    public Image getImage(){
        return new Image("file:src/main/resources/superheroes/fireman.png");
    }
    @Override
    public int getTime(Problem problem) {
        ProblemType problemType=problem.type;
        if (problemType == ProblemType.Fire) {
            return problem.getDaysToComplete() - 2;
        }
        return problem.getDaysToComplete();
    }
    @Override
    public boolean cannotGoTo(Vector2D position,CityMap map) {
        AbstractStaticObject object;
        if(position.follows(new Vector2D(0,0))&&position.precedes(new Vector2D(15,15)))
        {
            object = map.objectHere(position);
            if(( object instanceof Obstacle) && ((Obstacle)object).isSevere)
                return true;
            ArrayList<AbstractSuperhero> heroesHere=map.heroesAt(position);
            for(AbstractSuperhero heroHere:heroesHere){
                if(heroHere.isBusy())
                    return true;
            }
            return false;
        }
        return true;
    }
    public String toString(){
        return "SELECTED HERO: SUPERFIREMAN";
    }
}
