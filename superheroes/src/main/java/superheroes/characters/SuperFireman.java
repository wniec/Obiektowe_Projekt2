package superheroes.characters;

import superheroes.*;

public class SuperFireman extends AbstractSuperhero{
    public SuperFireman(){
        this.isBusy=false;
        this.maxDistance=4;
        this.hasHelicopter=false;
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
        if(position.follows(new Vector2D(0,0))&&position.precedes(new Vector2D(15,15)))
            return map.objectsHere(position) instanceof Obstacle &&((Obstacle)map.objectsHere(position)).isSevere;
        return true;
    }
}
