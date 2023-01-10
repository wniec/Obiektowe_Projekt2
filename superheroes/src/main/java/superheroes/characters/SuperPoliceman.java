package superheroes.characters;

import javafx.scene.image.Image;
import superheroes.Problem;
import superheroes.ProblemType;

public class SuperPoliceman extends AbstractSuperhero{
    public SuperPoliceman(){
        this.isBusy=false;
        this.maxDistance=4;
        this.availableDistance=4;
        this.hasHelicopter=false;
    }
    public Image getImage(){
        return new Image("file:src/main/resources/superheroes/policeman.png");
    }

    @Override
    public int getTime(Problem problem) {
        ProblemType problemType=problem.type;
        switch(problemType){
            case CriminalMystery -> {return problem.getDaysToComplete()-3;}
            case TechnicalProblem -> {return problem.getDaysToComplete()+2;}
            case SuperVillain -> {return problem.getDaysToComplete()-1;}
            default -> {return problem.getDaysToComplete();}
        }
    }
}
