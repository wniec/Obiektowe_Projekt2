package superheroes.characters;

import superheroes.Problem;
import superheroes.ProblemType;

public class SuperWarrior extends  AbstractSuperhero {
    public SuperWarrior(){
        this.isBusy=false;
        this.maxDistance=6;
        this.hasHelicopter=false;
    }
    @Override
    public int getTime(Problem problem) {
        ProblemType problemType=problem.type;
        switch(problemType){
            case CriminalMystery -> {return problem.getDaysToComplete()+2;}
            case SuperVillain -> {return 2;}
            default -> {return problem.getDaysToComplete();}
        }
    }
}
